package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.network

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemsCreacionAlbum
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataitemCommentsAlbum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject

class AlbumsService @Inject constructor(
    private val albumsListClient: AlbumsListClient,
    private val albumClient: AlbumClient,
    private val commentsAlbums: CommentsAlbums,
    private val createAlbums: CreateAlbums
) {

    //realizar post de comentario
    suspend fun postCommentsAlbums(albumId: String,body :DataitemCommentsAlbum) {
        return commentsAlbums.postCommentsAlbums(albumId, body)
    }

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

    //funcion para crear albums
    suspend fun CreateAlbums (body:DataItemsCreacionAlbum){
        return createAlbums.createAlbums(body)
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