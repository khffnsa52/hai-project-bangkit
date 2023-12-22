package com.capstone.hadirai.data.model

import com.capstone.hadirai.R

data class WelcomeImage(
    val title: String, val image: Int
)

val imageWelcome = listOf(
    WelcomeImage(
        "Unique Features - Face Detection Technology",
        R.drawable.img2
    ), WelcomeImage(
        "Proper Attendance - On-the-go attendance in accordance with company regulations",
        R.drawable.img3
    ), WelcomeImage(
        "Maintained Collaboration - Maintain harmony among company colleagues with the hAI app",
        R.drawable.img4
    )
)