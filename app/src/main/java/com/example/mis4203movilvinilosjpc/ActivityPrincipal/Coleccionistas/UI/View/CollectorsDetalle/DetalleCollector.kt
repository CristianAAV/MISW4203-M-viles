package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.UI.View.CollectorsDetalle

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
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.UI.ViewModel.CollectorViewModel
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.UI.ViewModel.LoadingState

@Composable
fun DetalleCollector(
    collectorViewModel: CollectorViewModel,
    navController: NavHostController,
    data:String ?){

    val collectorDetalleState by  collectorViewModel.collectorDetalleLoadingState.collectAsState()

    collectorViewModel.getCollector(data ?: "100")


    when (val loadingState = collectorDetalleState) {
        is LoadingState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = loadingState.errorMessage)
            }
        }
        LoadingState.Loading -> {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is LoadingState.Success -> {
            val collector = loadingState.data

            collectorViewModel.getAlbumsForCollectors(listOf(collector))
            DetalleCollectorUI(collector, collectorViewModel,navController)
        }
    }
}

