package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Network

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.DataItemArtista
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.DataPrizesClient
import retrofit2.http.GET
import retrofit2.http.Path

interface ArtistLisClient {
    //cliente consume servicio de toda lista de artista
    @GET("Musicians")
    suspend fun getArtistas(): List<DataItemArtista>
}

interface ArtistClient {
    //cliente consume servicio de un artista
    @GET("Musicians/{id}")
    suspend fun getArtist(@Path("id") id: String): DataItemArtista
}
//cliente consume servicio de todos los premios
interface PrizeClient {
    @GET("Prizes")
    suspend fun getAllPrizes(): List<DataPrizesClient>
}



