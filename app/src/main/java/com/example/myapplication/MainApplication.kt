package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.StartKoin


import org.koin.core.context.stopKoin
class MainApplication : Application() {
    var appcontext:android.content.Context? = null

    override fun onCreate() {
        super.onCreate()
        appcontext = this
        StartKoin.initKoin(this)
    }
    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
    companion object{
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}