package com.example.listadiscosexamen.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listadiscosexamen.data.Disco
import com.example.listadiscosexamen.data.DiscoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class HomeUiState(
    val discoList: List<Disco> = listOf(),
    val valoracionMedia: Double = 0.0
)

class HomeViewModel(
    private val discoRepository: DiscoRepository
) : ViewModel() {
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    init {
        viewModelScope.launch {
            discoRepository.getAll().map { discos ->
                val valoracionMedia = discos.map { it.valoracion.toDouble() }.average()
                HomeUiState(discoList = discos, valoracionMedia = valoracionMedia)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            ).collect {
                _homeUiState.value = it
            }
        }
    }

    fun deleteDisco(disco: Disco) {
        viewModelScope.launch {
            discoRepository.delete(disco)
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5000L
    }
}

