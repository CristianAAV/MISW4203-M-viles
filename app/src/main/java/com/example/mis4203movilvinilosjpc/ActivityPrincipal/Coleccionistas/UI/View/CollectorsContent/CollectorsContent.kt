package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.UI.View.CollectorsContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.UI.ViewModel.CollectorViewModel
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.UI.ViewModel.LoadingState

@Composable
fun CollectorsContent(
    collectorViewModel: CollectorViewModel,
    navController: NavController
) {
    // variable para controlar el estado de carga
    val collectorsViewModel = collectorViewModel.collectorsLoadingState.collectAsState()

    //variable para controlar el estado del boton.
    val enableButton by collectorViewModel.enableButton.observeAsState(true)

    when (val loadingState = collectorsViewModel.value) {

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
            val collectorList = loadingState.data

            LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    content = {
                    items(collectorList) { collector ->

                        CollectorCard(
                            enableButton = enableButton,
                            collector = collector,
                            navController = navController
                        ) {
                            collectorViewModel.onDetailsClick(
                                collector.id.toString(),
                                navController
                            )
                        }
                    }
                }
            )

        }
    }
}