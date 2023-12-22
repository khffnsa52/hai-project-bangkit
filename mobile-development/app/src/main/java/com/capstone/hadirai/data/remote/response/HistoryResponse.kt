package com.capstone.hadirai.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryResponse(
    @field:SerializedName("presensi_history") val presensiHistory: List<History>
) : Parcelable

@Parcelize
data class History(
    @field:SerializedName("date") val date: String,
    @field:SerializedName("img_url") val imgUrl: String,
    @field:SerializedName("time") val time: String
) : Parcelable