package com.example.listadiscosexamen.ui.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listadiscosexamen.data.DiscoRepository
import com.example.listadiscosexamen.ui.add.DiscoDetails
import com.example.listadiscosexamen.ui.add.toDiscoDetails
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

data class DetailUiScren (
    val discoDetails: DiscoDetails = DiscoDetails(),
    val errorLoading: Boolean = false
)

class DetailViewModel(
    savedStateHundle: SavedStateHandle,
    private val discoRepository: DiscoRepository
) : ViewModel() {
    var detailUiScren by mutableStateOf(DetailUiScren())
        private set

    init {
        val discoId = savedStateHundle.get<Int>("discoId") ?: -1
        if (discoId == -1) {
            detailUiScren = detailUiScren.copy(errorLoading = true)
        } else {
            val disco = discoRepository.get(discoId).filterNotNull().map {
                it.toDiscoDetails()
            }
            viewModelScope.launch {
                disco.collect {
                    updateUiState(it)
                }
            }
        }
    }

    private fun updateUiState(discoDetails: DiscoDetails) {
        detailUiScren = detailUiScren.copy(discoDetails = discoDetails)
    }

}