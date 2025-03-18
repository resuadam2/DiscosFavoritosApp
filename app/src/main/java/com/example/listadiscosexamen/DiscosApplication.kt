package com.example.listadiscosexamen

import android.app.Application
import com.example.listadiscosexamen.data.AppContainer

class DiscosApplication: Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}