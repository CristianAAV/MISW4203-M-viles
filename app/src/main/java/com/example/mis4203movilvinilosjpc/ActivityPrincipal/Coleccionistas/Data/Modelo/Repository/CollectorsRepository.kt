package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.Repository

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.Daos.TaskDaosCollectors
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.DataItemCollectors
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Network.CollectorsServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CollectorsRepository @Inject constructor(
    private val api: CollectorsServices,
    private val taskDaosCollectors: TaskDaosCollectors
){  //Api de colecionista de retrofit
   // fun getCollectorsFlow(): Flow<List<DataItemCollectors>> = api.getCollectorsFlow()
    fun getCollectorsFlow(): Flow<List<DataItemCollectors>> = flow {
       val cacheCollectors = taskDaosCollectors.getCollectors().firstOrNull() ?: emptyList()  //cache
       emit(cacheCollectors)

       if (cacheCollectors.isEmpty()) {
           val apiCollectors = api.getCollectorsFlow().firstOrNull() ?: emptyList()
           taskDaosCollectors.insertCollectors(apiCollectors)
           emit(apiCollectors)
       }
   }

    //api detalle coleccionista de retrifit
    fun getCollectorFlow(collectorId: String): Flow<DataItemCollectors> =
        api.getCollectorFlow(collectorId)
}