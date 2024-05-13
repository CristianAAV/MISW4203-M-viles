package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.Repositorio

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.Daos.TaskDaosArtists
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.DataItemArtista
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.DataPrizesClient
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Network.ArtistService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArtistRepository  @Inject constructor(
    private val api: ArtistService,
    private val taskDaosArtists: TaskDaosArtists) {
    //servicio de toda la lista de artista
    //fun getArtistFlow(): Flow<List<DataItemArtista>?> = api.getArtistsFlow()
    fun getArtistFlow(): Flow<List<DataItemArtista>> = flow {
        val cacheArtists = taskDaosArtists.getAlArtists().firstOrNull() ?: emptyList()
        emit(cacheArtists)
        if (cacheArtists.isEmpty()) {
            val apiArtists = api.getArtistsFlow().firstOrNull() ?: emptyList()
            taskDaosArtists.insertArtist(apiArtists)
            emit(apiArtists)
        }
    }


    //servicio de un artista
    fun getArtistDetalleFlow(artistId: String): Flow<DataItemArtista?> =
        api.getArtistFlow(artistId)

    fun getAllPrizes(): Flow<List<DataPrizesClient>> = api.getAllPrizesFlow()

    fun getPrizeFlow(prizeId: String): Flow<DataPrizesClient?> = api.getPrizeFlow(prizeId)

}