package com.example.myapplication.data.remote

import com.example.myapplication.data.remote.responses.PhotoJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("photos")
    suspend fun getPhotosList(
    ):Response<List<PhotoJson>>


    @GET("albums?")
    suspend fun getAlbumsById(
        @Query("id") id : Int
    ): retrofit2.Response<ArrayList<Any>>
}