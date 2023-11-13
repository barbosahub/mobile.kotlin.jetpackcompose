package com.example.myapplication.network.repository

import com.example.myapplication.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlbumsRepository(private val baseUrl: String) {
    suspend fun getPhotos() = withContext(Dispatchers.IO){
        val api = RetrofitInstance.getRetrofit(baseUrl)
        return@withContext api.getPhotos()
    }

    suspend fun getAlbumsById(id: Int) = withContext(Dispatchers.IO){
        val api = RetrofitInstance.getRetrofit(baseUrl)
        return@withContext api.getAlbumsById(id)
    }
}