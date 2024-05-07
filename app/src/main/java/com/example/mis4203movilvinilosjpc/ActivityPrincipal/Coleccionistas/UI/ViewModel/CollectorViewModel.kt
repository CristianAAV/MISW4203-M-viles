package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.UI.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Domine.AlbumsListUseCase
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.DataPrizesClient
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.CollectorAlbum
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.DataItemCollectors
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Domine.CollectorUseCase
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Domine.CollectorsListUseCase
import com.example.mis4203movilvinilosjpc.Navigation.AppScreem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectorViewModel @Inject constructor(
    private val collectorUseCase: CollectorUseCase,
    private val collectorsListUseCase: CollectorsListUseCase,
    private val albumsListUseCase: AlbumsListUseCase
):ViewModel() {

    //validamos el estado de la colecionista
    private val _collectorsLoadingState= MutableStateFlow<LoadingState<List<DataItemCollectors>>>(LoadingState.Loading)
    val collectorsLoadingState:StateFlow<LoadingState<List<DataItemCollectors>>> = _collectorsLoadingState

    //Validamos el estado de la carga del detalle Colecionista
    private val _collectorDetalleLoadingState = MutableStateFlow<LoadingState<DataItemCollectors>>(LoadingState.Loading)
    val collectorDetalleLoadingState:StateFlow<LoadingState<DataItemCollectors>> = _collectorDetalleLoadingState

    //creamos la variable que controla los albums por collecionista
    private val _albumsPorCollectors = MutableStateFlow<Map<Int, List<DataItemAlbums>>>(emptyMap())
    val albumsPorCollectors: StateFlow<Map<Int, List<DataItemAlbums>>> = _albumsPorCollectors

    init {
        getCollectors()
    }

    fun getAlbumsForCollectors(collectors: List<DataItemCollectors>) {
        viewModelScope.launch {
            try {
                val albumsPorCollectorsMap = mutableMapOf<Int, List<DataItemAlbums>>()
                val albumsMap = mutableMapOf<Int, DataItemAlbums>()

                // Obtener todos los albums disponibles y crear un mapeo entre su id y el objeto DataItemAlbums
                albumsListUseCase.invoke().collect { albums ->
                    albums.forEach { album ->
                        albumsMap[album.id.toInt()] = album
                    }
                }

                collectors.forEach { collector ->
                    val albums = mutableListOf<DataItemAlbums>()
                    collector.collectorAlbums.forEach { collectorAlbum ->
                        val album = albumsMap[collectorAlbum.id]
                        if (album != null) {
                            albums.add(album)
                        }
                    }
                    albumsPorCollectorsMap[collector.id] = albums
                }
                _albumsPorCollectors.value = albumsPorCollectorsMap
            } catch (e: Exception) {
                // Manejar el error adecuadamente
            }
        }
    }

    fun getCollectors(){
        viewModelScope.launch {
            collectorsListUseCase.invoke()
                .catch { e ->
                    _collectorsLoadingState.value = LoadingState.Error(e.message ?: "Error al cargar los colecionistas")
                }
                .collect { collectors ->
                    _collectorsLoadingState.value = LoadingState.Success(collectors)
                }
        }
    }

    fun getCollector(idCollector: String){
        viewModelScope.launch {
            collectorUseCase.invoke(idCollector)
                .catch { e ->
                    _collectorDetalleLoadingState.value = LoadingState.Error(e.message ?: "Error al cargar el colecionista")
                }
                .collect { collector ->
                    _collectorDetalleLoadingState.value = LoadingState.Success(collector)
                }
        }
    }

    fun onDetailsClick(idCollector: String, navController: NavController) {
        navController.navigate(route = AppScreem.DetalleColeccionista.createRoute(idCollector) )
    }

}

//clase cerrada para controlar el estado de la carga
sealed class LoadingState<out T> {//clase cerrada
object Loading : LoadingState<Nothing>()//carga
    data class Success<T>(val data: T) : LoadingState<T>()//carga exitosa
    data class Error(val errorMessage: String) : LoadingState<Nothing>()//error
}