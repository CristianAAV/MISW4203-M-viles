package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.UI.View.Artista

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.UI.ViewModel.ArtistaViewModel

@Composable
fun ArtistaContent(
    artistaViewModel: ArtistaViewModel,
    navController: NavController,
    modifier: Modifier
) {
    val artistaLoadingState by artistaViewModel.artistasLoadingState.collectAsState()


    //Controlamos el estado de carga
    when (val loadingState = artistaLoadingState) {
        //Si ha fallado
        is ArtistaViewModel.LoadingStateArtist.Error -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = loadingState.errorMessage)//Mensaje de Error
            }

        }
        //Si esta cargando infomacion
        ArtistaViewModel.LoadingStateArtist.Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator() //indicador de carga
            }
        }
        //
        is ArtistaViewModel.LoadingStateArtist.Success -> {
            val artistaList = loadingState.data //Obtenemos los datos
            LazyColumn(modifier = modifier.fillMaxSize().padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(artistaList!!) { artista ->
                    ArtistaCard(
                        artista = artista,
                        onClickDetalleArtista = {
                            artistaViewModel.onDetailsClick(artista.id.toString(), navController) },
                        navController = navController
                    )
                }
            }

            }
    }
}