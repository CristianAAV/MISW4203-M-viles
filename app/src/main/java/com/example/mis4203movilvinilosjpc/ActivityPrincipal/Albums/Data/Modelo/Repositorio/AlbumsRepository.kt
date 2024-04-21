package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Repositorio

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.network.AlbumsService

class AlbumsRepository {
    private val api = AlbumsService()
    suspend fun getAlbums(): List<DataItemAlbums> {
       return api.getAlbums()
    }

    suspend fun getAlbum(albumId: String):DataItemAlbums {
        return api.getAlbum(albumId)
    }


}