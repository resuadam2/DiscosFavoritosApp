package com.example.listadiscosexamen.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.listadiscosexamen.DiscosApplication
import com.example.listadiscosexamen.ui.add.AddViewModel
import com.example.listadiscosexamen.ui.detail.DetailViewModel
import com.example.listadiscosexamen.ui.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(discosApp().appContainer.provideDiscoRepository())
        }
        initializer {
            AddViewModel(discosApp().appContainer.provideDiscoRepository())
        }
        initializer {
            DetailViewModel(
                this.createSavedStateHandle(),
                discosApp().appContainer.provideDiscoRepository()
            )
        }
    }
}

fun CreationExtras.discosApp(): DiscosApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DiscosApplication)