package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.Repository

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.DataItemCollectors
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Network.CollectorsServices
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CollectorsRepository @Inject constructor(
    private val api: CollectorsServices
){  //Api de colecionista de retrofit
    fun getCollectorsFlow(): Flow<List<DataItemCollectors>> = api.getCollectorsFlow()

    //api detalle coleccionista de retrifit
    fun getCollectorFlow(collectorId: String): Flow<DataItemCollectors> =
        api.getCollectorFlow(collectorId)
}