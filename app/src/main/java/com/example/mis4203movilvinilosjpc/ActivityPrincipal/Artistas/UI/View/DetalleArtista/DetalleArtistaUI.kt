package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.UI.View.DetalleArtista

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.Album
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.DataItemArtista
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.DataPrizesClient
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.UI.ViewModel.ArtistaViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleArtistaUI(
    artista: DataItemArtista,
    artistaViewModel: ArtistaViewModel,
    navController: NavController,
) {

    val premiosPorArtista by artistaViewModel.premiosPorArtista.collectAsState(emptyMap())

    // Obtener los premios asociados a este artista
    val premiosDelArtista = premiosPorArtista[artista.id]

    Scaffold(
        topBar = {

            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Imagen del menu del drawer"
                        )
                    }
                },
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center, text = "detalle de Artista"
                    )
                }
            )
        }

    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                NameImageArtista(artista.image, artista.name, modifier = Modifier)
                Spacer(modifier = Modifier.padding(15.dp))
                DescriptionArtista(artista.description, modifier = Modifier)
                Spacer(modifier = Modifier.padding(4.dp))
                AlbumsArtista(artista.albums, modifier = Modifier)
                Spacer(modifier = Modifier.padding(4.dp))
                performerPrizes(premiosDelArtista, modifier = Modifier)

            }
        }
    }
}
///////////////Composables/////////////


//nombre artisata e imagen de artista
@Composable
fun NameImageArtista(image: String, name: String, modifier: Modifier) {

    Text(
        text = name,
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp
    )

    AsyncImage(
        model = image,
        contentDescription = "Imagen de perfil de artista",
        contentScale = ContentScale.Inside,
        modifier = modifier
            .fillMaxWidth()
            .size(200.dp, 200.dp),

        alignment = Alignment.Center
    )
}

//descripcion de artista
@Composable
fun DescriptionArtista(description: String, modifier: Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = description,
                textAlign = TextAlign.Justify,
                modifier = modifier.fillMaxWidth()
            )
        }

    }
}

//albums de artista
@Composable
fun AlbumsArtista(albums: List<Album>, modifier: Modifier) {

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = "Albums",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Column(modifier = modifier
                .fillMaxSize()
                .padding(8.dp)) {
                albums.forEachIndexed { index, album ->
                    Text(text = "${index + 1}. ${album.name}", fontSize = 15.sp)
                }
            }
        }


    }
}

//premios de artista
@Composable
fun performerPrizes(
    premiosDelArtista: List<DataPrizesClient?>?,
    modifier: Modifier.Companion,

    ) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(
                text = "Premios",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Column(modifier = modifier
                .fillMaxSize()
                .padding(8.dp)) {
                premiosDelArtista?.forEachIndexed { index, premio ->
                    Text(text = "${index + 1}. ${premio?.name}", fontSize = 15.sp)
                }
            }

        }

    }


}
