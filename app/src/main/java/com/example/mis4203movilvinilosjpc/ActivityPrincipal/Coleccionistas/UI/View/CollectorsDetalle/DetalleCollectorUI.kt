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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
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
                        textAlign = TextAlign.Center, text = "detalle de Coleccionista"
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
                        textAlign = TextAlign.Center
                    )
                }
                    Spacer(modifier = Modifier.padding(4.dp))
                //content collector telefono
                    DatosWhitTitleContent(title = "Numero de telefono" ,content =collector.telephone, modifier = Modifier)
                    Spacer(modifier = Modifier.padding(4.dp))
                //content collector email
                    DatosWhitTitleContent(title = "Email" ,content =collector.email, modifier = Modifier)
                    Spacer(modifier = Modifier.padding(4.dp))
                //content collector favoritePerformers
                    FavoritePerformers(collector =collector.favoritePerformers)
                    Spacer(modifier = Modifier.padding(4.dp))
                //content collector comentarios
                    Comments(collector =collector.comments)
                }

        }

    }

}

@Composable
fun Comments(collector: List<Comment>) {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column {
                Text(
                    text = "Comentarios",
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                collector.forEachIndexed() { index, comments ->
                    Text(
                        text = "${index + 1}. ${comments.description}",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Composable
fun FavoritePerformers(collector: List<FavoritePerformer>) {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column {
                Text(
                    text = "Artistas favoritos",
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                collector.forEachIndexed() { index, favoritePerformer ->
                    Text(
                        text = "${index + 1}. ${favoritePerformer.name}",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}





@Composable
fun DatosWhitTitleContent(title: String, content: String,modifier: Modifier) {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)) {
        Box(modifier = modifier.fillMaxSize()) {
            Column {
                Text(
                    text = title,
                    modifier = modifier,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                Text(
                    text = content,
                    modifier = modifier,
                    fontWeight = FontWeight.Light,
                    fontSize = 20.sp
                )

            }

        }
    }
}