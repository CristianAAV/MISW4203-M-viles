package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.network

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlbumsService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAlbums(): List<DataItemAlbums> {
      return  withContext(Dispatchers.IO){
          val response =  retrofit.create(AlbumsListClient::class.java).getAlbums()
            response.toList()
        }
    }
    suspend fun getAlbum(albumId: String): DataItemAlbums {
        return withContext(Dispatchers.IO) {
            retrofit.create(AlbumClient::class.java).getAlbum(albumId)
        }
    }
}