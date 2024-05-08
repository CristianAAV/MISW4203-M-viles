package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.UI.View.Artista

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.DataItemArtista

@Composable
fun ArtistaCard(
    artista: DataItemArtista,
    onClickDetalleArtista: () -> Unit,
    navController: NavController,
    enableButton: Boolean
) {



    Card (
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier.fillMaxWidth()
    ){
        Row (//creamos el contenido de la card en forma horizontal
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically //alineamos el contenido verticalmente
        ){
            ArtistInformation(
                artista = artista.name,
                modifier = Modifier.weight(0.7f),
                onClickDetalleArtista = onClickDetalleArtista,
                enabled = enableButton
                )
            ArtisCover(artista.image,modifier = Modifier.weight(0.3f))
        }
    }
}

@Composable
fun ArtistInformation(
    artista: String,
    modifier: Modifier,
    onClickDetalleArtista: () -> Unit,
    enabled: Boolean
) {
   Column (modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
       //nombre del artisa o banda
       Text(
           text = artista,
           fontSize = 20.sp,
           fontWeight = FontWeight.Bold
       )
       //Btn para ver detalles
       Button(
           enabled = enabled,
           onClick = onClickDetalleArtista
       )
       {
           Text(
               text = "ver detalles",
               fontSize = 16.sp)

       }
   }
}

@Composable
fun ArtisCover(imagen: String,modifier: Modifier) {
    //imagen con Coil
    AsyncImage(
        model = imagen,
        contentDescription = "Foto del artista",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(100.dp)
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .clip(CircleShape)
            .border(1.dp, color = Color.Blue, shape = CircleShape)
            .padding(end = 4.dp)
            .testTag("imagenAlbum"),

    )
}
