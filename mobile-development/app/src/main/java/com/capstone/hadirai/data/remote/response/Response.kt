package com.capstone.hadirai.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Response(
    @field:SerializedName("message") val message: String
) : Parcelable