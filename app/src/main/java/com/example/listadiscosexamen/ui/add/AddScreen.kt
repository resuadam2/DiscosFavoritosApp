package com.example.listadiscosexamen.ui.add

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.listadiscosexamen.ListaDiscosTopAppBar
import com.example.listadiscosexamen.ui.AppViewModelProvider
import com.example.listadiscosexamen.ui.navigation.NavigationDestination

object AddDestination : NavigationDestination {
    override val route = "add"
    override val title = "Añadir disco"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        topBar = {
            ListaDiscosTopAppBar(
                title = AddDestination.title,
                canNavigateBack = false,
            )
        },
    ){
        AddBody(
            onNavigateBack = onNavigateBack,
            modifier = modifier.padding(it),
            viewModel = viewModel
        )
    }
}

@Composable
fun AddBody(onNavigateBack: () -> Unit, modifier: Modifier, viewModel: AddViewModel) {
    TODO("Not yet implemented")
}