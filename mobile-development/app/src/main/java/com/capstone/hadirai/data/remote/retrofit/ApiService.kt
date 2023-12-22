package com.capstone.hadirai.data.remote.retrofit

import com.capstone.hadirai.data.remote.response.HistoryResponse
import com.capstone.hadirai.data.remote.response.Response
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("id_user") idUser: String,
        @Field("nama") nama: String
    ): Response

    @GET("history")
    suspend fun getHistory(
        @Query("id_user") idUser: String,
        @Query("nama") nama: String
    ): HistoryResponse

    @Multipart
    @POST("presensi")
    suspend fun uploadPresensi(
        @Part("id_user") idUser: RequestBody,
        @Part("nama") nama: RequestBody,
        @Part photo: MultipartBody.Part
    ): Response
}