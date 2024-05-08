package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.View.DetalleAlbum

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.ViewModel.AlbumsViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Comment
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Performer
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Track




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleAlbumUI(
    album: DataItemAlbums,
    albumsViewModel: AlbumsViewModel,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val comentarios by albumsViewModel.comentarios.observeAsState("")
    val isKeyBoardVisible by albumsViewModel.isKeyBoardVisible.observeAsState(false)
    val enableButtonBackStack by albumsViewModel.enableButtonBackStack.observeAsState(true)

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
                        textAlign = TextAlign.Center, text = "detalle de album"
                    )
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(){
            AlbumCover(album.cover, album.name, modifier = modifier)
            AlbumDetails(album, albumsViewModel, modifier = modifier)
            CommentSection(
                    album = album,
                    comentario = comentarios,
                    onComentarioChange ={albumsViewModel.onComentariosChange(it)},
                    isKeyBoardVisible = isKeyBoardVisible,
                    albumsViewModel = albumsViewModel,
                    modifier = modifier
                )
            }
        }
    }
}






    @Composable
 fun AlbumCover(coverUrl: String, title: String, modifier: Modifier) {
    Column(modifier = modifier) {
        //titulo del album
        AlbumTitle(title = title, modifier = modifier.align(Alignment.CenterHorizontally))
        //Imagen del album
        AsyncImage(
            model = coverUrl,
            contentDescription = "imagen del album detail", // Agregar descripción accesible
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .size(200.dp, 200.dp)
                .fillMaxWidth()
                .clip(RectangleShape)
                .padding(8.dp),
            contentScale = ContentScale.Fit
        )
    }
}


@Composable
 fun AlbumTitle(title: String, modifier: Modifier) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = modifier
            .padding(8.dp)
            .testTag("titleAlbumDetail")
    )
}



@Composable
fun AlbumDetails(
    album: DataItemAlbums,
    albumsViewModel: AlbumsViewModel,
    modifier: Modifier,
) {
    Column(modifier = modifier) {
        val espacio = 6;
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            AlbumArtist(album.performers)
            AlbumGenre(album.genre, modifier = modifier)
        }
        Spacer(modifier = Modifier.padding(espacio.dp))
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            modifier = modifier.fillMaxWidth()) {
            AlbumTracks(album.tracks)
        }
        Spacer(modifier = Modifier.padding(espacio.dp))
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            modifier = modifier.fillMaxWidth()) {
            AlbumReleaseDate(album.releaseDate, albumsViewModel)
        }
        Spacer(modifier = Modifier.padding(espacio.dp))

    }
}

@Composable
private fun AlbumArtist(performers: List<Performer>, modifier: Modifier = Modifier) {

    Row(modifier = modifier.padding(8.dp)) {
        Text(
            text = "Artista:",
            fontWeight = FontWeight.Bold

        )
        performers.forEach { performer ->
            Text(performer.name,
                modifier = Modifier.testTag("performansAlbumsDetail"))
        }
    }
}

@Composable
fun AlbumGenre(genre: String, modifier: Modifier) {

    Row(modifier = modifier.padding(8.dp)) {
        Text(
            text = "Género:",
            fontWeight = FontWeight.Bold
        )
        Text(genre,
            modifier = Modifier.testTag("generoAlbumsDetail"))
    }

}

@Composable
private fun AlbumTracks(tracks: List<Track>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(
            text = "Canciones:",
            fontWeight = FontWeight.Bold
        )
        tracks.forEachIndexed { index, track ->
            Text(text = "${index + 1}. ${track.name}",
                modifier = Modifier.testTag("singAlbumsDetail"))
        }
    }
}

@Composable
private fun AlbumReleaseDate(
    releaseDate: String,
    albumsViewModel: AlbumsViewModel,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.padding(8.dp)) {
        Text(
            text = "Fecha de lanzamiento:",
            fontWeight = FontWeight.Bold
        )
        val formattedDate = albumsViewModel.formatReleaseDate(releaseDate)
        Text(formattedDate,
            modifier = Modifier.testTag("dateAlbumsDetail"))
    }
}

@Composable
private fun CommentSection(
    album: DataItemAlbums,
    comentario: String,
    onComentarioChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isKeyBoardVisible: Boolean = false,
    albumsViewModel: AlbumsViewModel,
) {
    val view = LocalView.current
    val isKeyBoardVisebleState by rememberUpdatedState(newValue = isKeyBoardVisible)

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = modifier) {

            OutlinedTextField(
                value = comentario,
                onValueChange = onComentarioChange,
                label = { Text("Comentarios") },
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            albumsViewModel.showKeyboard()
                        } else {
                            albumsViewModel.hideKeyboard()
                        }
                    }
                    .testTag("textFieldcomentAlbumsDetail"),
                keyboardActions = KeyboardActions(onDone = {
                    albumsViewModel.hideKeyboard()
                }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            )
            Button(
                onClick = { /* Implementar lógica de envío de comentario */ },
                modifier = Modifier
                    .align(Alignment.End)
                    .testTag("btnComentAlbumsDetail")
            ) {
                Text("Enviar",
                    modifier = Modifier.testTag("textBtnComentAlbumsDetail"))
            }
            AlbumComments(album.comments)
        }
    }

    //ocultar el teclado cuando se pierde el focus
    SideEffect {
        if(!isKeyBoardVisebleState){
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
//
    }
}

@Composable
private fun AlbumComments(comments: List<Comment>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Comentarios:",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        comments.forEachIndexed { index, comment ->
            Text(
                text = "${index + 1}. ${comment.description}",
                modifier = Modifier
                    .padding(8.dp)
                    .testTag("commentAlbumsDetail")
            )
        }
    }
}