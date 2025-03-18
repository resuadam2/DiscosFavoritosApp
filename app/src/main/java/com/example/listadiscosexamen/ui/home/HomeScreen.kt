package com.example.listadiscosexamen.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.listadiscosexamen.ListaDiscosTopAppBar
import com.example.listadiscosexamen.ui.AppViewModelProvider
import com.example.listadiscosexamen.ui.navigation.NavigationDestination
import com.example.listadiscosexamen.R
import com.example.listadiscosexamen.data.Disco

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val title = "DiscosApp"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToAdd: () -> Unit,
    onNavigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val state by viewModel.homeUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ListaDiscosTopAppBar(
                title = HomeDestination.title,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            BottomAppBar(

            ) {
                Text(stringResource(R.string.average_rating,state.valoracionMedia.toString()))
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAdd,
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.disco_entry_title)
                )
            }
        }
    ) { innerPadding ->
        HomeBody(
            discos = state.discoList,
            onNavigateToDetail = onNavigateToDetail,
            onDeleteDisco = viewModel::deleteDisco,
            modifier = Modifier.padding(innerPadding)

        )
    }
}

@Composable
fun HomeBody(
    discos: List<Disco>,
    modifier: Modifier,
    onNavigateToDetail: (Int) -> Unit,
    onDeleteDisco: (Disco) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(discos) { 
            DiscoListItem(disco = it, onNavigateToDetail = onNavigateToDetail, onDeleteDisco = onDeleteDisco)
        }
    }
}

@Composable
fun DiscoListItem(
    disco: Disco,
    onNavigateToDetail: (Int) -> Unit,
    onDeleteDisco: (Disco) -> Unit
) {
    Row (
        modifier = Modifier.clickable(
            onClick = { onNavigateToDetail(disco.id) }
        )
    ){
        Column {
            Text(disco.titulo)
            Text(disco.autor)
        }
        for (i in 1..5) {
            if (i <= disco.valoracion) {
                Text("★")
            } else {
                Text("☆")
            }
        }
        IconButton(
            onClick = { onDeleteDisco(disco) }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.delete_button)
            )
        }
    }
}

@Preview
@Composable
fun DiscoListItemPreview() {
    DiscoListItem(
        disco = Disco(
            titulo = "Titulo",
            autor = "Autor",
            valoracion = 3,
            id = TODO(),
            numCanciones = TODO(),
            publicacion = TODO()
        ),
        onNavigateToDetail = {},
        onDeleteDisco = {}
    )
}

@Preview
@Composable
fun HomeBodyPreview() {
    HomeBody(
        discos = listOf(
            Disco(
                titulo = "Titulo",
                autor = "Autor",
                valoracion = 3,
                id = TODO(),
                numCanciones = TODO(),
                publicacion = TODO()
            ),
            Disco(
                titulo = "Titulo",
                autor = "Autor",
                valoracion = 3,
                id = TODO(),
                numCanciones = TODO(),
                publicacion = TODO()
            )
        ),
        onNavigateToDetail = {},
        onDeleteDisco = {},
        modifier = Modifier
    )
}