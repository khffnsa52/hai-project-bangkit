package com.capstone.hadirai.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.capstone.hadirai.data.pref.UserModel
import com.capstone.hadirai.data.pref.UserPreference
import com.capstone.hadirai.data.remote.response.HistoryResponse
import com.capstone.hadirai.data.remote.response.Response
import com.capstone.hadirai.data.remote.retrofit.ApiService
import com.capstone.hadirai.util.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class Repository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService
) {

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    fun login(idUser: String, nama: String): LiveData<ResultState<Response>> = liveData {
        emit(ResultState.Loading)
        try {
            val client = apiService.login(idUser, nama)
            emit(ResultState.Success(client))

        } catch (e: Exception) {
            Log.e("LoginViewModel", "login: ${e.message.toString()}")
            emit(ResultState.Error(e.message.toString()))
        }
    }

    fun uploadImage(
        idUser: String, nama: String, file: File
    ): LiveData<ResultState<Response>> = liveData {
        emit(ResultState.Loading)
        val idUserRequestBody = idUser.toRequestBody("text/plain".toMediaType())
        val namaRequestBody = nama.toRequestBody("text/plain".toMediaType())
        val requestImageFile = file.asRequestBody("image/jpeg".toMediaType())
        val multipartBody = MultipartBody.Part.createFormData(
            "photo", file.name, requestImageFile
        )
        try {
            val client =
                apiService.uploadPresensi(idUserRequestBody, namaRequestBody, multipartBody)
            emit(ResultState.Success(client))
        } catch (e: Exception) {
            Log.e("UploadViewModel", "uploadStories: ${e.message.toString()}")
            emit(ResultState.Error(e.message.toString()))
        }
    }

    fun getHistory(): LiveData<ResultState<HistoryResponse>> = liveData {
        emit(ResultState.Loading)
        val idUser: String = runBlocking { userPreference.getSession().first().idUser }
        val nama: String = runBlocking { userPreference.getSession().first().nama }
        try {
            val client = apiService.getHistory(idUser, nama)
            emit(ResultState.Success(client))
        } catch (e: Exception) {
            Log.e("HistoryViewModel", "getHistory: ${e.message.toString()}")
            emit(ResultState.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(userPreference, apiService)
            }.also { instance = it }
    }
}