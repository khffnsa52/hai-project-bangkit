package com.capstone.hadirai.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.hadirai.data.Repository
import com.capstone.hadirai.data.pref.UserModel
import com.capstone.hadirai.data.remote.response.Response
import com.capstone.hadirai.util.ResultState
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository) : ViewModel() {
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun login(idUser: String, nama: String): LiveData<ResultState<Response>> =
        repository.login(idUser, nama)
}