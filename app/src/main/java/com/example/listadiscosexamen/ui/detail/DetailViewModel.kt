package com.example.listadiscosexamen.ui.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listadiscosexamen.data.DiscoRepository
import com.example.listadiscosexamen.ui.add.DiscoDetails
import com.example.listadiscosexamen.ui.add.toDiscoDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val discoRepository: DiscoRepository
) : ViewModel() {

    companion object {
        private const val DETAIL_ID = "detailId"
    }

    var discoDetailUiState by mutableStateOf(DetailUiState())
        private set

    private val discoId: Int = checkNotNull(savedStateHandle[DetailDestination.detailIdArg])

    init {
        Log.d("DetailViewModel", "discoId: $discoId")
        val discoDetails = discoRepository.getDisco(discoId).filterNotNull().map {
            it.toDiscoDetails()
        }
        viewModelScope.launch {
            discoDetails.collect {
                updateUiState(it)
            }
        }
    }

    private fun updateUiState(details: DiscoDetails) {
        discoDetailUiState = DetailUiState(details)
    }

}

data class DetailUiState(
    val discoDetails: DiscoDetails = DiscoDetails(),
)