package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.UI.View.CollectorsDetalle

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.UI.ViewModel.CollectorViewModel
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.UI.ViewModel.LoadingState
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Data.Models.getStringResource
import com.example.mis4203movilvinilosjpc.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleCollector(
    collectorViewModel: CollectorViewModel,
    navController: NavHostController,
    data:String ?){

    val collectorDetalleState by  collectorViewModel.collectorDetalleLoadingState.collectAsState()
    val enableButtonBackStack by collectorViewModel.enableButtonBackStack.observeAsState(true)

    collectorViewModel.getCollector(data ?: "100")


    when (val loadingState = collectorDetalleState) {
        is LoadingState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = loadingState.errorMessage)
            }
        }
        LoadingState.Loading -> {
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("collectorTitleDetail")
                                    .semantics { contentDescription = "collectorTitleDetail" },
                                textAlign = TextAlign.Center, text = getStringResource(stringResId = R.string.detalleCollectors)
                            )
                        }
                    )
                }
            ) {
                Box(modifier = Modifier.fillMaxSize().padding(it), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
        is LoadingState.Success -> {
            val collector = loadingState.data

            collectorViewModel.getAlbumsForCollectors(listOf(collector))
            DetalleCollectorUI(collector, collectorViewModel,navController)
        }
    }
}

