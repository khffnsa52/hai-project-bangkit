package com.capstone.hadirai.model

import com.capstone.hadirai.R

data class User(
    val id: String,
    val imgUser : String,
    val nama: String,
    val company: String,
    val role: String,
    val password : String
)

val userMenu =
    User("abc",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg/330px-Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg",
        "Tulus",
                "Dicoding",
        "developer",
        "pass")
