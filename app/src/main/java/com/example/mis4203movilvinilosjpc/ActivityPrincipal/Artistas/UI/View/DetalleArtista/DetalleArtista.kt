package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.UI.View.DetalleArtista

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.UI.ViewModel.ArtistaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleArtista(
    artistaViewModel: ArtistaViewModel,
    navController: NavHostController,
    data:String ?
) {

    artistaViewModel.getArtistDetalle(data ?: "100")

    val artistaDetalleState by artistaViewModel.artistasLoadingStateDetalle.collectAsState()
    val enableButtonBackStack by artistaViewModel.enableButtonBackStack.observeAsState(true)



    //controlamos el estado de carga
  when (val loadingState = artistaDetalleState) {
      is ArtistaViewModel.LoadingStateArtist.Error -> {
          Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
              Text(text = loadingState.errorMessage)
          }
      }
      ArtistaViewModel.LoadingStateArtist.Loading -> {

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
                              onClick = { artistaViewModel.enableButton()
                                  navController.popBackStack() }) {
                              Icon(
                                  imageVector = Icons.Default.ArrowBack,
                                  contentDescription = "Imagen del menu del drawer"
                              )
                          }
                      },
                      title = {
                          Text(
                              textAlign = TextAlign.Center, text = "Detalle de Artista",
                              modifier = Modifier.fillMaxWidth()
                                  .testTag("artistaDetalleTitle")
                                  .semantics { contentDescription = "artistaDetalleTitle" }
                          )
                      }
                  )
              }

          ) {

              Box(modifier = Modifier.fillMaxSize().padding(it), contentAlignment = Alignment.Center) {
                  CircularProgressIndicator()
              }
          }
      }
      is ArtistaViewModel.LoadingStateArtist.Success -> {
          val artista = loadingState.data//obtenemos los datos

          artistaViewModel.getPrizesForArtists(listOf(artista!!))
          if (artista != null) {
              DetalleArtistaUI(artista, artistaViewModel,navController)
          }

      }
  }
}


