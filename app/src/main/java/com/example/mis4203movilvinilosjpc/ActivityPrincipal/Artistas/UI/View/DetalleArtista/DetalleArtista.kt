package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.UI.View.DetalleArtista

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.UI.ViewModel.ArtistaViewModel

@Composable
fun DetalleArtista(
    artistaViewModel: ArtistaViewModel,
    navController: NavHostController,
    data:String ?
) {

    val artistaDetalleState by artistaViewModel.artistasLoadingStateDetalle.collectAsState()

    artistaViewModel.getArtistDetalle(data ?: "100")

    //controlamos el estado de carga
  when (val loadingState = artistaDetalleState) {
      is ArtistaViewModel.LoadingStateArtist.Error -> {
          Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
              Text(text = loadingState.errorMessage)
          }
      }
      ArtistaViewModel.LoadingStateArtist.Loading -> {

          Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
              CircularProgressIndicator()
          }
      }
      is ArtistaViewModel.LoadingStateArtist.Success -> {
          val artista = loadingState.data//obtenemos los datos



          DetalleArtistaUI(artista, artistaViewModel,navController)
      }
  }
}


