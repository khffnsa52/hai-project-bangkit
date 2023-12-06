package com.capstone.hadirai.model

import com.capstone.hadirai.R

data class Menu(
    val image: Int,
    val title: String,
    val price: String
)

val dummyMenu = listOf(
    Menu(R.drawable.button, "Button1", "CA"),
    Menu(R.drawable.button2, "Button2", "ACDS"),
    Menu(R.drawable.button3, "Button3", "VSVS")
)