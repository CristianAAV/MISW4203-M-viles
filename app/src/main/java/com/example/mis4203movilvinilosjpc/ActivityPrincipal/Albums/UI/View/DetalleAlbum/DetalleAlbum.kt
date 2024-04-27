package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.View.DetalleAlbum


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.ViewModel.AlbumsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleAlbum(
    navController: NavController,
    albumsViewModel: AlbumsViewModel,
    data: String?,
    modifier: Modifier = Modifier) {

    //variable para controlar el estado del estado de carga de la api, esta enganchada a viewModel
    val albumDetalleLoadingState = albumsViewModel.albumDetalleLoadingState.collectAsState()

    albumsViewModel.getAlbum(data ?: "100") // Llama a getAlbum() solo si data no es nulo


        //controlamos el estado de carga
        when (val loadingState = albumDetalleLoadingState.value) {
            //si ha fallado, mostramos un error
            is AlbumsViewModel.LoadingState.Error -> {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = loadingState.errorMessage)//error
                }
            }
            // si esta cargando, mostramos un indicador de carga
            AlbumsViewModel.LoadingState.Loading -> {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator() //indicador de carga
                }
            }
            //si funciona el cargue de la api bien, mostramos los datos
            is AlbumsViewModel.LoadingState.Success -> {
                val album = loadingState.data //obtenemos los datos
                DetalleAlbumUI(album, albumsViewModel, modifier,navController)

            }
        }
    }





