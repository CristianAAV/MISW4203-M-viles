package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Network

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.DataItemArtista
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.DataPrizesClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArtistService @Inject constructor(
    private val artistListClient: ArtistLisClient,
    private val artistClient: ArtistClient,
    private val prizeClient: PrizeClient
){
    //servicio de toda la lista de artista
    fun getArtistsFlow(): Flow<List<DataItemArtista>?> = flow {
        try {
            val response = artistListClient.getArtistas()
            emit(response)
        } catch (e: Exception) {
            emit(null)
        }
    }
    //servicio de un artista
    fun getArtistFlow(artistId: String): Flow<DataItemArtista?> = flow {
        try {
            val response = artistClient.getArtist(artistId)
            emit(response)
        } catch (e: Exception) {
            emit(null)
        }
    }
    //servicio de un premio
    fun getPrizeFlow(prizeId: String): Flow<DataPrizesClient?> = flow {
        try {
            val response = prizeClient.getPrize(prizeId)
            emit(response)
        } catch (e: Exception) {
            emit(null)
        }
    }
}