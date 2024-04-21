package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.View


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.ViewModel.AlbumsViewModel
import com.example.mis4203movilvinilosjpc.Navigation.AppScreem


@Composable
fun AlbumContent(albumsViewModel: AlbumsViewModel,navController: NavController) {
    val albumList: List<DataItemAlbums> by albumsViewModel.albumsList.observeAsState(emptyList())
    albumsViewModel.getAlbums()
    LazyColumn() {
        items(albumList) {datos->
            Card(
                modifier = Modifier.fillMaxWidth(),
                 ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    InformacionAlbum(Modifier.weight(1f), albumsViewModel,datos,navController)
                    imagenesAlbum(Modifier.weight(0.5f), albumsViewModel,datos)
                }

            }
            // Agregar un espacio entre las tarjetas
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun InformacionAlbum(
    modifier: Modifier,
    albumsViewModel: AlbumsViewModel,
    datos: DataItemAlbums,
    navController: NavController
) {
    Column(
        modifier =modifier
            .wrapContentSize()
            ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = datos.name,
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold)

        datos.performers.forEach{performer->
            Text(text = performer.name, textAlign = TextAlign.Center, fontSize = 15.sp)
        }

        Button(onClick = { navController.navigate(AppScreem.DetalleAlbum.createRoute(datos.id)) }) {
            Text(text = "Detalles")
        }
    }
}

@Composable
fun imagenesAlbum(modifier: Modifier, albumsViewModel: AlbumsViewModel, datos: DataItemAlbums) {
    Box(modifier =modifier.wrapContentSize() ){
        //imagen  cargada del producto
        AsyncImage(
            model = datos.cover,
            modifier = Modifier.size(80.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Blue, CircleShape)
                .padding(end = 4.dp),
            contentDescription = "cover del album",
            contentScale = ContentScale.Crop
        )




    }
}