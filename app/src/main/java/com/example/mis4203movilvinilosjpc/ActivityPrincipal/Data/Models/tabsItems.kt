package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Data.Models

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.View.AlbumsContent.AlbumContent
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.ViewModel.AlbumsViewModel


typealias composablefun = @Composable (NavController,AlbumsViewModel) -> Unit

sealed class tabsItems(
    var title: String,
    var screem: composablefun


    ) {
    object Album : tabsItems("Albums", {navController, albumsViewModel ->
        AlbumContent(navController = navController, albumsViewModel = albumsViewModel)

       // AlbumContent(albumsViewModel = viewModel(), navController = it)

    })

    object Artista : tabsItems("Artistas", {navController, albumsViewModel ->

    })
    object Coleccionista : tabsItems("Coleccionistas", {navController, albumsViewModel ->

    })
}