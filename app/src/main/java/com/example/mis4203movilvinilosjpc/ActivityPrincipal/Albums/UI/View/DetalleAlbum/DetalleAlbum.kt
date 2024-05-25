package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.View.DetalleAlbum


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.ViewModel.AlbumsViewModel
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Data.Models.getStringResource
import com.example.mis4203movilvinilosjpc.R


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
    val enableButtonBackStack by albumsViewModel.enableButtonBackStack.observeAsState(true)

        //controlamos el estado de carga
        when (val loadingState = albumDetalleLoadingState.value) {
            //si ha fallado, mostramos un error
            is AlbumsViewModel.LoadingState.Error -> {
                Scaffold(
                    topBar = {

                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            navigationIcon = {
                                IconButton(
                                    enabled = enableButtonBackStack,
                                    onClick = { albumsViewModel.enableButton()
                                        navController.popBackStack() }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Imagen del menu del drawer"
                                    )
                                }
                            },
                            title = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center, text = getStringResource(R.string.detalleAlbum)
                                )
                            }
                        )
                    }
                ) {
                    Box(modifier = modifier.fillMaxSize().padding(it), contentAlignment = Alignment.Center) {
                        Text(text = loadingState.errorMessage)//error
                    }
                }

            }
            // si esta cargando, mostramos un indicador de carga
            AlbumsViewModel.LoadingState.Loading -> {
                Scaffold(
                    topBar = {

                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            navigationIcon = {
                                IconButton(
                                    enabled = enableButtonBackStack,
                                    onClick = { albumsViewModel.enableButton()
                                        navController.popBackStack() }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Imagen del menu del drawer"
                                    )
                                }
                            },
                            title = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center, text = getStringResource(R.string.detalleAlbum)
                                )
                            }
                        )
                    }
                ) {
                    Box(modifier = modifier.fillMaxSize().padding(it), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator() //indicador de carga
                    }
                }
            }
            //si funciona el cargue de la api bien, mostramos los datos
            is AlbumsViewModel.LoadingState.Success -> {
                val album = loadingState.data //obtenemos los datos
                DetalleAlbumUI(album, albumsViewModel, modifier,navController)

            }
        }
    }





