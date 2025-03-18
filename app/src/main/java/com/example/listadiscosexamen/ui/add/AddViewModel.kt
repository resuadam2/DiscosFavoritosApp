package com.example.listadiscosexamen.ui.add

import android.database.sqlite.SQLiteConstraintException
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.example.listadiscosexamen.data.Disco
import com.example.listadiscosexamen.data.DiscoRepository

data class AddUiState(
    val discoDetails: DiscoDetails = DiscoDetails(),
    val isSaveButtonClicked: Boolean = false
)

class AddViewModel(
    private val discoRepository: DiscoRepository,
) : ViewModel() {
    var addUiState = AddUiState()
        private set

    fun updateUiState(discoDetails: DiscoDetails) {
        addUiState = addUiState.copy(discoDetails = discoDetails,
            isSaveButtonClicked = validateInput(discoDetails))
    }

    suspend fun saveDisco() : Boolean {
        if (validateInput()) {
            try {
                discoRepository.insert(addUiState.discoDetails.toDisco())
                return true
            } catch (e: SQLiteConstraintException) {
                return false
            }
        } else return false
    }

    private fun validateInput(discoDetails: DiscoDetails = addUiState.discoDetails): Boolean {
        return with(discoDetails) {
            discoDetails.titulo.isNotBlank() && discoDetails.autor.isNotBlank()
                    && discoDetails.numCanciones.isNotBlank() && discoDetails.publicacion.isNotBlank()
                    && discoDetails.numCanciones.isDigitsOnly() && discoDetails.publicacion.isDigitsOnly()
                    && discoDetails.numCanciones.toInt() > 0 && discoDetails.publicacion.toInt() > 999
                    && discoDetails.numCanciones.toInt() < 100 && discoDetails.publicacion.toInt() < 2030
        }
    }
}

data class DiscoDetails(
    val id: Int = 0,
    val titulo: String = "",
    val autor: String = "",
    val numCanciones: String = "",
    val publicacion: String = "",
    var valoracion: String = "0",
)

fun Disco.toDiscoDetails(): DiscoDetails {
    return DiscoDetails(
        id = id,
        titulo = titulo,
        autor = autor,
        numCanciones = numCanciones.toString(),
        publicacion = publicacion.toString(),
        valoracion = valoracion.toString()
    )
}

fun DiscoDetails.toDisco(): Disco {
    return Disco(
        id = id,
        titulo = titulo,
        autor = autor,
        numCanciones = numCanciones.toInt(),
        publicacion = publicacion.toInt(),
        valoracion = valoracion.toInt()
    )
}