package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Data.Models

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.View.AlbumsContent.AlbumContent
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.UI.View.Artista.ArtistaContent
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.UI.View.CollectorsContent.CollectorsContent


typealias composablefun = @Composable (NavController,ExtendedViewModel) -> Unit

sealed class tabsItems(
    var title: String,
    var screem: composablefun


    ) {
    object Album : tabsItems("Albums", {navController, extendendViewModel ->
        AlbumContent(navController = navController, albumsViewModel = extendendViewModel.albumsViewModel)

       // AlbumContent(albumsViewModel = viewModel(), navController = it)

    })

    object Artista : tabsItems("Artistas", {navController, extendendViewModel ->
        ArtistaContent(artistaViewModel = extendendViewModel.artistaViewModel, navController = navController,modifier = Modifier)

    })
    object Coleccionista : tabsItems("Coleccionistas", {navController, extendendViewModel ->
        CollectorsContent(collectorViewModel =extendendViewModel.collectorViewModel, navController = navController)
    })
}