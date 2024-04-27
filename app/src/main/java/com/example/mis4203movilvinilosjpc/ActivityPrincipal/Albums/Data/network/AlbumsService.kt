package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.network

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject

class AlbumsService @Inject constructor(
    private val albumsListClient: AlbumsListClient,
    private val albumClient: AlbumClient,
) {

    fun getAlbumsFlow(): Flow<List<DataItemAlbums>> = flow {
        try {
            val response = albumsListClient.getAlbums()
            emit(response)
        } catch (e: Exception) {
            // Manejar excepciones aquí
        }
    }

    fun getAlbumFlow(albumId: String): Flow<DataItemAlbums> = flow {
        try {
            val response = albumClient.getAlbum(albumId)
            emit(response)
        } catch (e: Exception) {
            // Manejar excepciones aquí
        }
    }

    /* suspend fun getAlbums(): List<DataItemAlbums> {
       return  withContext(Dispatchers.IO){
           val response =  retrofit.create(AlbumsListClient::class.java).getAlbums()
             response.toList()
         }
     }
     suspend fun getAlbum(albumId: String): DataItemAlbums {
         return withContext(Dispatchers.IO) {
             retrofit.create(AlbumClient::class.java).getAlbum(albumId)
         }
     }*/
}