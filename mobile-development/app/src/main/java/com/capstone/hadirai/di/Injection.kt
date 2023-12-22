package com.capstone.hadirai.di

import android.content.Context
import com.capstone.hadirai.data.Repository
import com.capstone.hadirai.data.pref.UserPreference
import com.capstone.hadirai.data.pref.dataStore
import com.capstone.hadirai.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(pref, apiService)
    }
}