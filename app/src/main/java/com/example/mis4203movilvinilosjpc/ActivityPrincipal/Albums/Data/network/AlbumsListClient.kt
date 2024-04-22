package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.network

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import retrofit2.http.GET
import retrofit2.http.Path

//cliente que cosume  el servicio
interface AlbumsListClient {
    @GET("albums")
    suspend fun getAlbums(): List<DataItemAlbums>
}

// Cliente que consume el servicio
interface AlbumClient {
    @GET("albums/{albumId}")
    suspend fun getAlbum(@Path("albumId") albumId: String): DataItemAlbums
}