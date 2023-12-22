package com.capstone.hadirai.ui.presensi

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.hadirai.data.Repository
import com.capstone.hadirai.data.pref.UserModel
import java.io.File

class PresensiViewModel(private val repository: Repository) : ViewModel() {

    fun uploadPresensi(idUser: String, nama: String, file: File) =
        repository.uploadImage(idUser, nama, file)

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}