package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.network

import androidx.room.Update
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemsCreacionAlbum
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataitemCommentsAlbum
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
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

// Post llenar Comentario
interface CommentsAlbums {
    @POST("albums/{albumId}/comments")
    suspend fun postCommentsAlbums(@Path("albumId")albumId: String,@Body body: DataitemCommentsAlbum)
}

// crear post crear un albums
interface CreateAlbums {
    @POST("albums")
    suspend fun createAlbums (@Body body:DataItemsCreacionAlbum)
}
