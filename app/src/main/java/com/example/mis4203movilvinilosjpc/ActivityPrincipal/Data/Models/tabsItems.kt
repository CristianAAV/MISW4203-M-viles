package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Data.Models

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.View.AlbumContent
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.ViewModel.AlbumsViewModel


typealias composablefun = @Composable (NavController) -> Unit

sealed class tabsItems(
    var title: String,
    var screem: composablefun

) {
    object Album : tabsItems("Album", {

        AlbumContent(albumsViewModel = AlbumsViewModel(), navController = it)

    })

    object Artista : tabsItems("Artista", {

    })
    object Coleccionista : tabsItems("Coleccionista", {

    })
}