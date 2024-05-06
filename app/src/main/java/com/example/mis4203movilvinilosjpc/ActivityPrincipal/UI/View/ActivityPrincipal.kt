package com.example.mis4203movilvinilosjpc.ActivityPrincipal.UI.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.ViewModel.AlbumsViewModel
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.UI.ViewModel.ArtistaViewModel
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.UI.ViewModel.CollectorViewModel
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.UI.View.Tabs.TabsContent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityPrincipal(
    navController: NavController,
    albumsViewModel: AlbumsViewModel,
    artistaViewModel: ArtistaViewModel,
    collectorViewModel: CollectorViewModel,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center, text = "Vinilos"
                    )

                }
            )
        },

        ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TabsContent(navController,albumsViewModel,artistaViewModel,collectorViewModel)
        }
    }
}