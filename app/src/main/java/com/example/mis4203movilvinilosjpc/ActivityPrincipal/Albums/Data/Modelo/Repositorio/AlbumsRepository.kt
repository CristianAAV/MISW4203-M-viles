package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Repositorio

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.network.AlbumsService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlbumsRepository @Inject constructor(private val api : AlbumsService) {


    fun getAlbumsFlow(): Flow<List<DataItemAlbums>> = api.getAlbumsFlow()

    fun getAlbumFlow(albumId: String): Flow<DataItemAlbums> = api.getAlbumFlow(albumId)

    /*
    suspend fun getAlbums(): List<DataItemAlbums> {
       return api.getAlbums()
    }

    suspend fun getAlbum(albumId: String):DataItemAlbums {
        return api.getAlbum(albumId)
    }
*/
}