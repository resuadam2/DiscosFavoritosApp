package com.example.listadiscosexamen.data

import android.content.Context

class AppContainer(context: Context) {
    private val appDatabase = AppDatabase.getDatabase(context)
    private val discoDao = appDatabase.discoDao()
    private val discoRepository = DiscoRepositoryImpl(discoDao)

    fun provideDiscoRepository(): DiscoRepository = discoRepository
}