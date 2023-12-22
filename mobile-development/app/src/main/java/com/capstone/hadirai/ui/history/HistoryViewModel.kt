package com.capstone.hadirai.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.hadirai.data.Repository
import com.capstone.hadirai.data.pref.UserModel
import com.capstone.hadirai.data.remote.response.HistoryResponse
import com.capstone.hadirai.util.ResultState

class HistoryViewModel(private val repository: Repository) : ViewModel() {

    fun getHistory(): LiveData<ResultState<HistoryResponse>> = repository.getHistory()

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}