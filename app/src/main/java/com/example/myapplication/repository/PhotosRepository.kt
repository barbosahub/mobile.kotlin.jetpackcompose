package com.example.myapplication.repository

import com.example.myapplication.data.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotosRepository(private val baseUrl: String) {
    suspend fun getPhotosList() = withContext(Dispatchers.IO){
        val api = RetrofitInstance.getRetrofit(baseUrl)
        return@withContext api.getPhotosList()
    }

    suspend fun getAlbumsById(id: Int) = withContext(Dispatchers.IO){
        val api = RetrofitInstance.getRetrofit(baseUrl)
        return@withContext api.getAlbumsById(id)
    }
}