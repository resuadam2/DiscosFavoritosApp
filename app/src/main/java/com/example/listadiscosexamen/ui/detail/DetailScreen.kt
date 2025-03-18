package com.example.listadiscosexamen.ui.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
                canNavigateBack = false,
            )
        },
    ){
        DetailsBody(
            onNavigateBack = onNavigateBack,
            modifier = modifier.padding(it),
            viewModel = viewModel
        )
    }
}

@Composable
fun DetailsBody(onNavigateBack: () -> Unit, modifier: Modifier, viewModel: DetailViewModel) {
    TODO("Not yet implemented")
}