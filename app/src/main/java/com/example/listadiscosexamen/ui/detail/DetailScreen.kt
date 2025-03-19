package com.example.listadiscosexamen.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.listadiscosexamen.ListaDiscosTopAppBar
import com.example.listadiscosexamen.ui.AppViewModelProvider
import com.example.listadiscosexamen.ui.navigation.NavigationDestination

object DetailDestination : NavigationDestination {
    override val route = "detail"
    override val title = "Detail"
    const val detailIdArg = "detailId"
    val routeWithArgs = "$route/{$detailIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        topBar = {
            ListaDiscosTopAppBar(
                title = DetailDestination.title,
                canNavigateBack = true,
                navigateUp = onNavigateBack
            )
        },
    ){
        DetailsBody(
            discoDetails = viewModel.detailUiScren,
            modifier = modifier.padding(it),
        )
    }
}

@Composable
fun DetailsBody(discoDetails: DetailUiScren, modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Details")
        Row {
            Text("Name: ${discoDetails.discoDetails.titulo}")
        }
        Row {
            Text("Artist: ${discoDetails.discoDetails.autor}")
        }
        Row {
            Text("Number of songs: ${discoDetails.discoDetails.numCanciones}")
        }
        Row {
            Text("Year: ${discoDetails.discoDetails.publicacion}")
        }
        Row {
            Text("Valoration: ${discoDetails.discoDetails.valoracion}")
        }

    }
}
