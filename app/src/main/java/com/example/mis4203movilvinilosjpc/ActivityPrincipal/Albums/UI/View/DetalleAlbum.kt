package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.View


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.FontScaling
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.ViewModel.AlbumsViewModel
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleAlbum(navController: NavController, albumsViewModel: AlbumsViewModel, data: String?) {
    albumsViewModel.getAlbum(data ?: "")

    val albumState: State<List<DataItemAlbums>?> = albumsViewModel.album.observeAsState()
    val album: List<DataItemAlbums>? = albumState.value

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
                        textAlign = TextAlign.Center, text = "detalle de album"
                    )
                }
            )
        },

        ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            ImagenAlbum(album)
            AlbumDetalle(albumsViewModel, album)

        }
    }
}

@Composable
fun ImagenAlbum(album: List<DataItemAlbums>?) {
    //Nombre del album
    Column {
        album?.get(0)?.name?.let {
            Text(
                text = it,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
//imagen  cargada del producto
        AsyncImage(
            model = album?.get(0)?.cover,
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp)
                .clip(RectangleShape)
                .padding(end = 4.dp),
            contentDescription = "cover del album",
            contentScale = ContentScale.Fit
        )
    }

}


@Composable
fun AlbumDetalle(albumsViewModel: AlbumsViewModel, album: List<DataItemAlbums>?) {
    val comentarios by albumsViewModel.comentarios.observeAsState("")

    LazyColumn {
        item {
            //artista y genero
            Card(modifier = Modifier.fillMaxWidth()) {
                album?.get(0)?.performers?.get(0)?.name?.let {
                    Row {
                        Text(
                            text = "Artista:",
                            modifier = Modifier.padding(5.dp),
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp
                        )
                        Text(text = it, modifier = Modifier.padding(5.dp))
                    }

                }
                album?.get(0)?.genre?.let {
                    Row {
                        Text(
                            text = "Genero:",
                            modifier = Modifier.padding(5.dp),
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp
                        )
                        Text(text = it, modifier = Modifier.padding(5.dp))
                    }

                }
            }
            Spacer(modifier = Modifier.height(1.dp))
            // Canciones
            Card(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Canciones:",
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                )
                album?.get(0)?.tracks?.let { tracks ->
                    var numeracion:Int = 1
                    for (cancion in tracks) {
                        Text(text = "${numeracion ++}) "+cancion.name, modifier = Modifier.padding(5.dp))
                    }

                }
            }
            Spacer(modifier = Modifier.height(1.dp))
            //fecha de lanzamiento
            Card(modifier = Modifier.fillMaxWidth()) {
                album?.get(0)?.releaseDate?.let {
                    Row {
                        Text(
                            text = "Fecha de lanzamiento:",
                            modifier = Modifier.padding(5.dp),
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp
                        )
                        Log.d("comentarios", "datos ${it}")
                        val date = convertirfecha(it)
                        Log.d("comentarios", "datos ${date}")
                        Text(
                            text = date,
                            modifier = Modifier
                                .padding(5.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }

                }

            }
            Spacer(modifier = Modifier.height(1.dp))

            OutlinedTextField(
                value = comentarios,
                onValueChange = { albumsViewModel.onComentariosChange(it) },
                label = { Text(text = "Comentarios") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Box(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Text(text = "Enviar")
                }
            }
            Spacer(modifier = Modifier.height(1.dp))

            //comentarios
            Card(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Comentarios:",
                    modifier = Modifier.padding(5.dp),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                )
                album?.get(0)?.comments?.let { comentarios ->
                    var numeracion:Int = 1
                    for (comments in comentarios) {

                        Text(text ="${numeracion  }) "+ comments.description, modifier = Modifier.padding(5.dp))
                        numeracion++
                    }

                }
            }
            Spacer(modifier = Modifier.height(1.dp))

        }


    }

}


fun convertirfecha(releaseDate: String): String {
    val formatoEntrada = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val formatoSalida = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    val fechaParseada: Date = formatoEntrada.parse(releaseDate) ?: Date()
    return formatoSalida.format(fechaParseada)
}
