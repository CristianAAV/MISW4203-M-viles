package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.UI.View.CollectorsDetalle

import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.Comment
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.DataItemCollectors
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.FavoritePerformer
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.UI.ViewModel.CollectorViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleCollectorUI(
    collector: DataItemCollectors,
    collectorViewModel: CollectorViewModel,
    navController: NavHostController) {

    val albumsCollectors by collectorViewModel.albumsPorCollectors.collectAsState(emptyMap())

    val albumsForCollectors = albumsCollectors[collector.id]

    val enableButtonBackStack by collectorViewModel.enableButtonBackStack.observeAsState(true)

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
                        onClick = {
                            collectorViewModel.enableButton()
                            navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Imagen del menu del drawer"
                        )
                    }
                },
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth()
                            .testTag("collectorTitleDetail")
                            .semantics { contentDescription = "collectorTitleDetail" },
                        textAlign = TextAlign.Center, text = "Detalle de Coleccionista"
                    )
                }
            )
        }
    ) {it ->

        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .padding(it)) {
            item{
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                   //title collector
                    Text(
                        text = collector.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                            .testTag("collectorNameDetail")
                            .semantics { contentDescription = "collectorNameDetail" }
                    )
                }
                    Spacer(modifier = Modifier.padding(4.dp))
                //content collector telefono
                    DatosWhitTitleContent(
                        title = "Numero de telefono" ,
                        content =collector.telephone,
                        modifier = Modifier
                            .testTag("collectorNumberDetail")
                            .semantics { contentDescription = "collectorNumberDetail" })
                    Spacer(modifier = Modifier.padding(4.dp))
                //content collector email
                    DatosWhitTitleContent(title = "Email" , content =collector.email,modifier = Modifier
                        .testTag("collectorEmailDetail")
                        .semantics { contentDescription = "collectorEmailDetail" })
                    Spacer(modifier = Modifier.padding(4.dp))
                //content collector favoritePerformers
                    FavoritePerformers(
                            collector =collector.favoritePerformers)
                    Spacer(modifier = Modifier.padding(4.dp))
                //content collector comentarios
                    Comments(collector =collector.comments)
                    Spacer(modifier = Modifier.padding(4.dp))
                //content collector albums
                    CollectorAlbumList(albums = albumsForCollectors ?: emptyList())


                }

        }

    }

}

@Composable
fun CollectorAlbumList(albums: List<DataItemAlbums>) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        if (albums.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Text(
                    text = "Albunes de coleccionista",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("collectorAlbumText")
                        .semantics { contentDescription = "collectorAlbumText" }
                )
                Column(modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp)) {
                    albums.forEachIndexed { index, dataItemAlbums ->
                        Text(text = "${index + 1}. ${dataItemAlbums.name}",fontSize = 15.sp,
                            modifier = Modifier
                                .testTag("collectorAlbumDetail")
                                .semantics { contentDescription = "collectorAlbumDetail" })

                    }
                }
            }
        } else {
            Text(
                text = "No hay álbumes para este coleccionista",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
        }
    }
}



    @Composable
    fun Comments(collector: List<Comment>) {
        Card( elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(
                        text = "Comentarios",
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("collectorCommentText")
                            .semantics { contentDescription = "collectorCommentText" },
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Box(modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp)) {
                        collector.forEachIndexed() { index, comments ->
                            Text(
                                text = "${index + 1}. ${comments.description}",
                                fontSize = 15.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("collectorCommentDetail")
                                    .semantics { contentDescription = "collectorCommentDetail" }
                            )
                        }
                    }
                }

        }
    }

    @Composable
    fun FavoritePerformers(collector: List<FavoritePerformer>) {
        Card( elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )  {
                    Text(
                        text = "Artistas favoritos",
                        modifier = Modifier
                            .testTag("collectorArtistText")
                            .semantics { contentDescription = "collectorArtistText" },
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Box(modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp)) {
                        collector.forEachIndexed() { index, favoritePerformer ->
                            Text(
                                text = "${index + 1}. ${favoritePerformer.name}",
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .testTag("collectorArtistDetail")
                                    .semantics { contentDescription = "collectorArtistDetail" }
                            )
                        }
                    }
                }

        }
    }

    //composable de contenidos general
    @Composable
    fun DatosWhitTitleContent(title: String, content: String, modifier: Modifier) {
        Card( elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(
                        text = title,
                        modifier = modifier,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Box(modifier = modifier.fillMaxSize().padding(horizontal = 8.dp)) {
                        Text(
                            text = content,
                            modifier = modifier,
                            fontSize = 15.sp
                        )

                    }
                }


        }
    }

