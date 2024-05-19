package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.View.AlbumsContent


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.ViewModel.AlbumsViewModel
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.ViewModel.CreateAlbumsViewModel


@Composable
fun AlbumContent(
    albumsViewModel: AlbumsViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    createAlbumsViewModel: CreateAlbumsViewModel,
) {
    //variable para controlar el estado del estado de carga de la api, esta enganchada a viewModel
    val albumsLoadingState = albumsViewModel.albumsLoadingState.collectAsState()

    //variable para controlar el estado del boton.
    val enableButton by albumsViewModel.enableButton.observeAsState(true)


    //controlamos el estado de carga
    when (val loadingState = albumsLoadingState.value) {
        // si esta cargando, mostramos un indicador de carga
        is AlbumsViewModel.LoadingState.Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator() //indicador de carga
            }
        }

        //si ha fallado, mostramos un error
        is AlbumsViewModel.LoadingState.Error -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = loadingState.errorMessage)//error
            }
        }
        //si todo ha ido bien, mostramos los datos
        is AlbumsViewModel.LoadingState.Success -> {
            val albumList = loadingState.data //obtenemos los datos
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)) {


                //creamos el recycler view en forma vertical
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                ) {
                    items(albumList) { album ->

                        //mostramos cada tarjeta
                        AlbumCard(
                            album = album,
                            onDetailsClick = {
                                albumsViewModel.onDetailsClick(
                                    album.id,
                                    navController
                                )
                            },
                            enableButton = enableButton
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                //mostramos el floating button
                Box(modifier = Modifier.fillMaxSize()){
                    floatingButton(
                        enableButton = enableButton, createAlbumsViewModel = createAlbumsViewModel,
                        navController = navController,modifier = modifier.align(Alignment.BottomEnd)
                    ){
                        createAlbumsViewModel.navegar(navController)
                    }

                }

            }
        }


    }
}
@Composable
fun floatingButton(
    enableButton: Boolean,
    createAlbumsViewModel: CreateAlbumsViewModel,
    navController: NavController,
    modifier: Modifier,
    function: () -> Unit,
) {
    FloatingActionButton(onClick = { function ()},
        modifier = modifier.padding(16.dp)) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar Album")

    }

}







