package com.capstone.hadirai.model

data class Pengumuman(
    val title: String,
    val detail: String,
    val date: String
)
val dummyPengumuman = listOf(
    Pengumuman("Pengumuman 1", "Hari inii", "Senin, 20 Novemeber 2023"),
    Pengumuman("Pengumuman 2", "Hari inii yaa", "Senin, 21 Novemeber 2023"),
    Pengumuman("Pengumuman 3", "Hari inii okee", "Senin, 22 Novemeber 2023"),
    Pengumuman("Pengumuman 4", "Hari inii okee", "Senin, 22 Novemeber 2023"),
    Pengumuman("Pengumuman 5", "Hari inii", "Senin, 20 Novemeber 2023"),
    Pengumuman("Pengumuman 6", "Hari inii yaa", "Senin, 21 Novemeber 2023"),
    Pengumuman("Pengumuman 7", "Hari inii okee", "Senin, 22 Novemeber 2023"),
    Pengumuman("Pengumuman 8", "Hari inii okee", "Senin, 22 Novemeber 2023"),
)