package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Data.Models

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.ViewModel.AlbumsViewModel
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.UI.ViewModel.ArtistaViewModel
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.UI.ViewModel.CollectorViewModel

data class ExtendedViewModel(
    val albumsViewModel: AlbumsViewModel,
    val artistaViewModel: ArtistaViewModel,
    val collectorViewModel: CollectorViewModel
)
