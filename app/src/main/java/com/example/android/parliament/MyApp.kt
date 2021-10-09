package com.example.android.parliament

import android.app.Application
import android.content.Context

//Giang Nguyen - 30.09.2021

//Global App Context
class MyApp: Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}