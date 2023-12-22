package com.capstone.hadirai.data.pref

data class UserModel(
    val idUser: String, val nama: String, val token: String, val isLogin: Boolean = false
)