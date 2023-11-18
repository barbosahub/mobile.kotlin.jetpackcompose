package com.example.myapplication.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("photos")
    suspend fun getPhotosList(
    ):Response<Map<String, Any>>


    @GET("albums?")
    suspend fun getAlbumsById(
        @Query("id") id : Int
    ): Response<Map<String, Any>>
}