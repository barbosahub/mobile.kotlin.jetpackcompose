package com.example.myapplication.di

import com.example.myapplication.MainApplication.Companion.BASE_URL
import com.example.myapplication.network.RetrofitInstance
import com.example.myapplication.network.repository.AlbumsRepository
import com.example.myapplication.ui.home.viewModel.AlbumsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Repositories
    single{ AlbumsRepository(BASE_URL) }
    //ViewModels
    viewModel { AlbumsViewModel(get()) }
    //Retrofit
    single { RetrofitInstance }
}