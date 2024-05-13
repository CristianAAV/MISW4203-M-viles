package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Network

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.DataItemCollectors
import retrofit2.http.GET
import retrofit2.http.Path

interface CollectorListCLient {
    //endPoint para obtenemos los datos de collecionista
    @GET("collectors")
    suspend fun getCollectors(): List<DataItemCollectors>

}
interface CollectorClient {
    @GET("collectors/{collectorId}")
    suspend fun getCollector(@Path("collectorId") collectorId: String): DataItemCollectors
}