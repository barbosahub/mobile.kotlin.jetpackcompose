package com.example.myapplication.di

import com.example.myapplication.MainApplication.Companion.BASE_URL
import com.example.myapplication.data.network.RetrofitInstance
import com.example.myapplication.repository.PhotosRepository
import com.example.myapplication.presentation.photoslist.PhotosViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Repositories
    single{ PhotosRepository(BASE_URL) }
    //ViewModels
    viewModel { PhotosViewModel(get()) }
    //Retrofit
    single { RetrofitInstance }
}