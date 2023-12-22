package com.capstone.hadirai.ui.presensi

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.capstone.hadirai.databinding.ActivityPresensiBinding
import com.capstone.hadirai.ui.ViewModelFactory
import com.capstone.hadirai.ui.camera.CameraXActivity
import com.capstone.hadirai.ui.camera.CameraXActivity.Companion.CAMERAX_RESULT
import com.capstone.hadirai.ui.customview.MyButton
import com.capstone.hadirai.ui.history.HistoryFragment
import com.capstone.hadirai.ui.main.MainActivity
import com.capstone.hadirai.util.Classifier
import com.capstone.hadirai.util.ResultState
import com.capstone.hadirai.util.reduceFileImage
import com.capstone.hadirai.util.uriToFile

class PresensiActivity : AppCompatActivity() {
    private lateinit var bitmap: Bitmap
    private lateinit var mClassifier: Classifier
    private val timer: Long = 3000
    private lateinit var handler: Handler
    private val inputSize = 150
    private val mModelPath = "model.tflite"
    private val mLabelPath = "label.txt"
    private lateinit var binding: ActivityPresensiBinding
    private var currentImageUri: Uri? = null
    private lateinit var nama: String
    private lateinit var idUser: String

    private lateinit var detectButton: MyButton
    private lateinit var uploadButton: MyButton
    private var checkDetectButton: Boolean = false
    private var checkUploadButton: Boolean = false


    private val viewModel by viewModels<PresensiViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this,
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPresensiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        mClassifier = Classifier(assets, mModelPath, mLabelPath, inputSize)

        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        detectButton = binding.detectButton
        uploadButton = binding.uploadButton

        binding.galleryButton.setOnClickListener { startGallery() }
        binding.cameraXButton.setOnClickListener { startCameraX() }
        binding.uploadButton.setOnClickListener { uploadImage(idUser, nama) }
        detectButton()
        setButtonEnabled()
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }


    private fun startCameraX() {
        val intent = Intent(this, CameraXActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERAX_RESULT) {
            currentImageUri = it.data?.getStringExtra(CameraXActivity.EXTRA_CAMERAX_IMAGE)?.toUri()
            showImage()
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.previewImageView.setImageURI(it)
        }
        checkDetectButton = true
        setButtonEnabled()

    }

    private fun uploadImage(idUser: String, nama: String) {
        binding.uploadButton.visibility = View.INVISIBLE
        checkUploadButton = false
        setButtonEnabled()
        currentImageUri?.let { uri ->
            val imageFile = uriToFile(uri, this).reduceFileImage()
            Log.d("Image File", "showImage: ${imageFile.path}")
            viewModel.uploadPresensi(idUser, nama, imageFile).observe(this) { result ->
                if (result != null) {
                    when (result) {
                        is ResultState.Loading -> {
                            showLoading(true)
                        }

                        is ResultState.Success -> {
                            showToast("Upload Successful")
                            showLoading(false)
                            val intent = Intent(this, MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                            finish()
                        }

                        is ResultState.Error -> {
                            showToast("Upload failed")
                            showLoading(false)
                        }
                    }
                }
            }
        } ?: showToast("blank image")
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun recognizeImage() {
        bitmap = (binding.previewImageView.drawable as BitmapDrawable).bitmap
        bitmap = scaleImage(bitmap)
        val results = mClassifier.recognizeImage(bitmap).firstOrNull()
        val nama_result = results?.title
        val confidence = results?.confidence
        showLoading(false)
        checkResult(nama_result!!, confidence!!)
    }

    private fun checkResult(nama_result: String, confidence: Float) {
        viewModel.getSession().observe(this) {
            nama = it.nama
            idUser = it.idUser
            val result : String = if(nama ==  nama_result) "Correct Image" else "Incorrect Image"
            binding.result.visibility = View.VISIBLE
            binding.namePercentage.text = nama_result + " " + confidence.times(100)?.toInt().toString() + "%"
            binding.resultText.text = result
            checkUploadButton = nama == nama_result
            checkDetectButton = !checkUploadButton
            setButtonEnabled()
        }
    }

    private fun detectButton() {
        binding.detectButton.setOnClickListener {
            handler = Handler()
            handler.postDelayed({
                recognizeImage()
            }, timer)
            showLoading(true)
            checkDetectButton = false
            setButtonEnabled()
        }
    }

    private fun scaleImage(bitmap: Bitmap?): Bitmap {
        val orignalWidth = bitmap!!.width
        val originalHeight = bitmap.height
        val scaleWidth = inputSize.toFloat() / orignalWidth
        val scaleHeight = inputSize.toFloat() / originalHeight
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        return Bitmap.createBitmap(bitmap, 0, 0, orignalWidth, originalHeight, matrix, true)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setButtonEnabled() {
        detectButton.visibility = if (checkDetectButton) View.VISIBLE else View.GONE
        detectButton.isEnabled = checkDetectButton
        uploadButton.visibility = if (checkUploadButton) View.VISIBLE else View.INVISIBLE
        uploadButton.isEnabled = checkUploadButton
    }
}