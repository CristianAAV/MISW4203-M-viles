package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Repositorio

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.compose.ui.platform.LocalContext
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Daos.TaskDaosAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemsCreacionAlbum
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataitemCommentsAlbum
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.network.AlbumsService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AlbumsRepository @Inject constructor(
    private val api : AlbumsService,
    private val taskDaosAlbums: TaskDaosAlbums,
    @ApplicationContext private val context: Context) {

   //fun getAlbumsFlow(): Flow<List<DataItemAlbums>> = api.getAlbumsFlow()
   fun getAlbumsFlow(): Flow<List<DataItemAlbums>> = flow {

       val apiAlbums = api.getAlbumsFlow().firstOrNull() ?: emptyList()
       taskDaosAlbums.insertAlbums(apiAlbums)
       val cachedAlbums = taskDaosAlbums.getAlbums().firstOrNull() ?: emptyList()

       if (cachedAlbums.isEmpty()) {
           taskDaosAlbums.insertAlbums(apiAlbums)
           emit(apiAlbums)
       }
       else{
           emit(cachedAlbums)
           val albumsFlow = taskDaosAlbums.getAlbums().map { albums ->
               albums.toList()
           }
           emitAll(albumsFlow)
           //todo update cache
       }
   }

    fun getAlbumFlow(albumId: String): Flow<DataItemAlbums> = api.getAlbumFlow(albumId)

    //funcion para llamar al servicio que envía un post de comentario
    suspend fun postCommentsAlbums(albumId: String,body : DataitemCommentsAlbum) {
        api.postCommentsAlbums(albumId, body)
    }

    suspend fun postCreacionAlbums(body:DataItemsCreacionAlbum){
        api.CreateAlbums(body)
    }

       // Función para verificar si hay conexión a Internet
       fun isNetworkAvailable(): Boolean {
           val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
               val network = connectivityManager.activeNetwork ?: return false
               val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
               return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
           } else {
               val networkInfo = connectivityManager.activeNetworkInfo ?: return false
               return networkInfo.isConnected
           }
       }
}