package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Data.Models

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.View.AlbumsContent.AlbumContent
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.UI.View.Artista.ArtistaContent
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.UI.View.CollectorsContent.CollectorsContent
import com.example.mis4203movilvinilosjpc.R
import androidx.compose.ui.platform.LocalContext
import com.example.mis4203movilvinilosjpc.ResourceProvider


typealias composablefun = @Composable (NavController,ExtendedViewModel) -> Unit


@Composable
fun getStringResource(@StringRes stringResId: Int): String {
    val context = LocalContext.current
    return context.getString(stringResId)
}

sealed class tabsItems(
    var title: String,
    var screem: composablefun

    ) {



    object Album : tabsItems(ResourceProvider.getString(R.string.nameAlbum), { navController, extendendViewModel ->
        AlbumContent(navController = navController, albumsViewModel = extendendViewModel.albumsViewModel,
            createAlbumsViewModel = extendendViewModel.createAlbumsViewModel)

    })

    object Artista : tabsItems(ResourceProvider.getString(R.string.nameArtists), {navController, extendendViewModel ->
        ArtistaContent(artistaViewModel = extendendViewModel.artistaViewModel, navController = navController,modifier = Modifier)

    })
    object Coleccionista : tabsItems(ResourceProvider.getString(R.string.nameCollectors), {navController, extendendViewModel ->
        CollectorsContent(collectorViewModel =extendendViewModel.collectorViewModel, navController = navController)
    })
}