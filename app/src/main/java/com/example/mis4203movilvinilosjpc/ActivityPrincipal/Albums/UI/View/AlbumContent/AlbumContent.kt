package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.View.AlbumsContent



import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.ViewModel.AlbumsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun AlbumContent(
    albumsViewModel: AlbumsViewModel ,
    navController: NavController,
    modifier: Modifier = Modifier)

{
    //variable para controlar el estado del estado de carga de la api, esta enganchada a viewModel
    val albumsLoadingState = albumsViewModel.albumsLoadingState.collectAsState()



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

            //creamos el recycler view en forma vertical
            LazyColumn(modifier = modifier) {
                items(albumList) { album ->
                    //mostramos cada tarjeta
                    AlbumCard(
                        album = album,
                        onDetailsClick = { AlbumsViewModel.onDetailsClick(album.id, navController) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }


    }
}





