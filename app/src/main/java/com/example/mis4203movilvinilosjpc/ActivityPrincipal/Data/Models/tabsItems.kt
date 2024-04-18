package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Data.Models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.UI.View.Tabs.AlbumContent

typealias composablefun = @Composable () -> Unit

sealed class tabsItems(
    var title : String,
    var screem : composablefun
) {
    object Album: tabsItems("Album",{
        AlbumContent()
    })
    object Artista: tabsItems("Artista",{})
    object Coleccionista: tabsItems("Coleccionista",{})
}