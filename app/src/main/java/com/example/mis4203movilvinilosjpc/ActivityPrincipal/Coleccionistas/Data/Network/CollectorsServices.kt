package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Network

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.DataItemCollectors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CollectorsServices @Inject constructor(
    private val CollectorListClient: CollectorListCLient,
    private val CollectorClient: CollectorClient
) {
    //Servicio para obtener los datos del listado decollecionista
    fun getCollectorsFlow(): Flow<List<DataItemCollectors>> = flow {
        try {
            val response = CollectorListClient.getCollectors()
            emit(response)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
    //Servicio para obtenemos los datos de collecionista
    fun getCollectorFlow(collectorId: String): Flow<DataItemCollectors> = flow {
        try {
            val response = CollectorClient.getCollector(collectorId)
            emit(response)
        } catch (e: Exception) {
            // Manejar excepciones aquí
        }
    }


}