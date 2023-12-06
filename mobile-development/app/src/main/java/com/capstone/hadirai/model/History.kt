package com.capstone.hadirai.model

import com.capstone.hadirai.R

data class History(
    val id: String,
    val date : String,
    val waktuMasuk: String?,
    val imgAwal : String?,
    val waktuKeluar: String?,
    val imgAkhir : String?

)
val historyDummy = listOf(
    History("abc", "Senin, 20 November 2023","07.30", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg/330px-Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg", "18.45", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg/330px-Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg"),
    History("abd", "Senin, 21 November 2023","07.30", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg/330px-Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg", "18.45", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg/330px-Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg"),
    History("abe", "Senin, 22 November 2023","07.30", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg/330px-Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg", "18.45", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg/330px-Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg"),
    History("abf", "Senin, 23 November 2023","07.30", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg/330px-Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg", "18.45", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg/330px-Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg"),
    History("abg", "Senin, 24 November 2023","07.30", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg/330px-Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg", "18.45", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg/330px-Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg"),
    History("abh", "Senin, 25 November 2023","07.30", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg/330px-Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg", "18.45", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg/330px-Tulus_Performs_at_Jakarta_International_Jazz_Java_Festival_2020_%28cropped%29.jpg")


)