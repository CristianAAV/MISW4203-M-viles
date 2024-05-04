package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.UI.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.DataItemArtista
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.DataPrizesClient
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Domine.ArtistDetalleUseCase
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Domine.ArtistListUseCase
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Domine.PrizeClientUseCase
import com.example.mis4203movilvinilosjpc.Navigation.AppScreem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistaViewModel @Inject constructor(
    private val artistListUseCase: ArtistListUseCase,
    private val artistDetalleUseCase: ArtistDetalleUseCase,
    private val prizeClientUseCase: PrizeClientUseCase
): ViewModel() {

    // MutableStateFlow para controlar el estado de carga de los artistas
    private val _artistasLoadingState = MutableStateFlow<LoadingStateArtist<List<DataItemArtista>>>(LoadingStateArtist.Loading)
    val artistasLoadingState: StateFlow<LoadingStateArtist<List<DataItemArtista>>> = _artistasLoadingState

    // MutableStateFlow para controlar el estado de carga del detalle de un artista
    private val _artistasLoadingStateDetalle = MutableStateFlow<LoadingStateArtist<DataItemArtista>>(LoadingStateArtist.Loading)
    val artistasLoadingStateDetalle: StateFlow<LoadingStateArtist<DataItemArtista>> = _artistasLoadingStateDetalle

    // MutableStateFlow para almacenar los datos de premios
    private val _prizeData = MutableStateFlow<DataPrizesClient?>(null)
    val prizeData: StateFlow<DataPrizesClient?> = _prizeData

    init {
        // Al iniciar el ViewModel, obtenemos la lista de artistas
        getArtistas()
    }

    // Función para obtener los datos de un premio
    fun getPrize(prizeId: String) {
        viewModelScope.launch {
            prizeClientUseCase.invoke(prizeId)
                .catch {
                    // Manejar el error
                }
                .collect {
                    _prizeData.value = it
                }
        }
    }

    // Función para obtener la lista de artistas
    fun getArtistas() {
        viewModelScope.launch {
            artistListUseCase.invoke()
                .catch {
                    // En caso de error, actualizamos el estado con un mensaje de error
                    _artistasLoadingState.value = LoadingStateArtist.Error(it.message ?: "Error al cargar los artistas")
                }
                .collect {
                    // Cuando se obtienen los datos exitosamente, actualizamos el estado con los datos
                    _artistasLoadingState.value = LoadingStateArtist.Success(it)
                }
        }
    }

    // Función para obtener el detalle de un artista
    fun getArtistDetalle(artistaId: String) {
        viewModelScope.launch {
            artistDetalleUseCase.invoke(artistaId)
                .catch {
                    // En caso de error, actualizamos el estado con un mensaje de error
                    _artistasLoadingStateDetalle.value = LoadingStateArtist.Error(it.message ?: "Error al cargar el artista")
                }
                .collect {
                    // Cuando se obtienen los datos exitosamente, actualizamos el estado con los datos
                    _artistasLoadingStateDetalle.value = LoadingStateArtist.Success(it)
                }
        }
    }

    // Clase sellada para controlar el estado de carga
    sealed class LoadingStateArtist<out T> {
        object Loading : LoadingStateArtist<Nothing>()
        data class Success<T>(val data: T?) : LoadingStateArtist<T>()
        data class Error(val errorMessage: String) : LoadingStateArtist<Nothing>()
    }

    // Función encargada del botón de detalle de un artista
    /*fun onDetailsClick(artistaId: String, navController: NavController) {
        // Control para ir a la pantalla de detalle del artista
        navController.navigate(route = AppScreem.DetalleArtista.createRoute(artistaId))
    }*/
}
