package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.UI.View.CollectorsContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.DataItemCollectors

@Composable
fun CollectorCard(
    enableButton : Boolean,
    collector: DataItemCollectors,
    navController: NavController,
    onClickDetalleCollector: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier.size(150.dp),

    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {


            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )
            {
                Text(
                    text = collector.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.0.sp,
                    modifier = Modifier.fillMaxWidth()
                        .testTag("collectorName")
                        .semantics { contentDescription = "collectorName" }
                )
                Spacer(modifier = Modifier.padding(top = 4.dp))
                Button(
                    enabled = enableButton,
                    onClick = { onClickDetalleCollector() }) {
                    Text(
                        text = "Ver Detalle",
                        fontSize = 16.0.sp,
                        modifier = Modifier
                            .testTag("collectorButtonDetail")
                            .semantics { contentDescription = "collectorButtonDetail" }
                    )
                }

            }
        }
    }

}
    
