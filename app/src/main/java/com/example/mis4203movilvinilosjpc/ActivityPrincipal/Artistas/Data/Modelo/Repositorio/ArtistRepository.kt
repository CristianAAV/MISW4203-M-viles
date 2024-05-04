package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.Repositorio

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.DataItemArtista
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.DataPrizesClient
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Network.ArtistService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtistRepository  @Inject constructor(private val api: ArtistService) {
    //servicio de toda la lista de artista
    fun getArtistFlow(): Flow<List<DataItemArtista>> = api.getArtistsFlow()
    //servicio de un artista
    fun getArtistDetalleFlow(artistId: String): Flow<DataItemArtista> =
        api.getArtistFlow(artistId)
    //servicio de un premio
    fun getPrizeFlow(prizeId: String): Flow<DataPrizesClient> =
        api.getPrizeFlow(prizeId)
}