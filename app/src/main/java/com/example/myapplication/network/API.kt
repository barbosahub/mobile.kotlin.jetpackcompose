package com.example.myapplication.network

import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("photos")
    suspend fun getPhotos(
    ): retrofit2.Response<ArrayList<Any>>


    @GET("albums?")
    suspend fun getAlbumsById(
        @Query("id") id : Int
    ): retrofit2.Response<ArrayList<Any>>
}