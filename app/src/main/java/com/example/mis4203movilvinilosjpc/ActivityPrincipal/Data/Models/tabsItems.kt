package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Data.Models

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.View.AlbumContent
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.UI.ViewModel.ActivityPrincipalViewModel

typealias composablefun = @Composable (NavController) -> Unit

sealed class tabsItems(
    var title: String,
    var screem: composablefun

) {
    object Album : tabsItems("Album", {

        AlbumContent(albumsViewModel = AlbumsViewModel(), navController = it)

    })

    object Artista : tabsItems("Artista", {
        ActivityPrincipalViewModel().titleChange("Artista")
    })
    object Coleccionista : tabsItems("Coleccionista", {
        ActivityPrincipalViewModel().titleChange("Coleccionista")
    })
}