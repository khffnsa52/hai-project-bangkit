package com.capstone.hadirai.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstone.hadirai.data.pref.UserModel
import com.capstone.hadirai.databinding.ActivityLoginBinding
import com.capstone.hadirai.ui.ViewModelFactory
import com.capstone.hadirai.ui.customview.IdUserEditText
import com.capstone.hadirai.ui.customview.MyButton
import com.capstone.hadirai.ui.customview.PasswordEditText
import com.capstone.hadirai.ui.main.MainActivity
import com.capstone.hadirai.util.ResultState

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var passwordEditText: PasswordEditText
    private lateinit var idUserEditText: IdUserEditText
    private lateinit var nameEditText: TextView
    private lateinit var button: MyButton
    private var checkIdUser: Boolean = false
    private var checkName: Boolean = false
    private var checkPassword: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
        playAnimation()
        checkButton()
    }

    private fun setupView() {
        @Suppress("DEPRECATION") if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {

            val idUser = binding.idUserEditText.text.toString()
            val nama = binding.nameEditText.text.toString()
            viewModel.login(idUser, nama).observe(this) { result ->
                if (result != null) {
                    when (result) {
                        is ResultState.Loading -> {
                            showLoading(true)
                        }

                        is ResultState.Success -> {
                            showLoading(false)
                            showToast("Login successful")

                            val user = UserModel(idUser, nama, "sample_token")
                            viewModel.saveSession(user)
                            viewModel.getSession().observe(this) { user ->
                                if (user.token.isNotEmpty() && user.token.isNotBlank()) {
                                    startActivity(Intent(this, MainActivity::class.java))
                                    finish()
                                }
                            }
                        }

                        is ResultState.Error -> {
                            showLoading(false)
                            showToast("Login failed")
                        }
                    }
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(100)
        val nameTextView =
            ObjectAnimator.ofFloat(binding.nameTextView, View.ALPHA, 1f).setDuration(100)
        val nameEditTextLayout =
            ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val idUserTextView =
            ObjectAnimator.ofFloat(binding.idUserTextView, View.ALPHA, 1f).setDuration(100)
        val idUserEditTextLayout =
            ObjectAnimator.ofFloat(binding.idUserEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(100)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val login = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(
                title,
                nameTextView,
                nameEditTextLayout,
                idUserTextView,
                idUserEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                login
            )
            startDelay = 100
        }.start()
    }

    private fun checkButton() {
        passwordEditText = binding.passwordEditText
        idUserEditText = binding.idUserEditText
        nameEditText = binding.nameEditText
        button = binding.loginButton


        idUserEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val pattern = "[0-9]*"
                checkIdUser = !s.isNullOrEmpty() && Regex(pattern).matches(s.toString())
                setButtonEnabled()

            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        nameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkName = !s.isNullOrEmpty()
                setButtonEnabled()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkPassword = !s.isNullOrEmpty() && s.toString().length > 7
                setButtonEnabled()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun setButtonEnabled() {
        button.isEnabled = checkIdUser && checkName && checkPassword
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}