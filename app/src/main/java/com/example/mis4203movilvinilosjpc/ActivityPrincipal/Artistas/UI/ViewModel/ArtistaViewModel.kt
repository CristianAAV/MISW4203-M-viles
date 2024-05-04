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
):ViewModel() {

//creamos la clase que controla el estado de la carga de la api
    private val _artistasLoadingState = MutableStateFlow<LoadingStateArtist<List<DataItemArtista>>>(LoadingStateArtist.Loading)
    val artistasLoadingState: StateFlow<LoadingStateArtist<List<DataItemArtista>>> = _artistasLoadingState

    //creamos la clase que controla el estado de la carga de la api
    private val _artistasLoadingStateDetalle = MutableStateFlow<LoadingStateArtist<DataItemArtista>>(LoadingStateArtist.Loading)
    val artistasLoadingStateDetalle: StateFlow<LoadingStateArtist<DataItemArtista>> = _artistasLoadingStateDetalle

    //creamos la clase que controla el estado de la carga de la api
    private val _prizeData = MutableStateFlow<DataPrizesClient?>(null)
    val prizeData: StateFlow<DataPrizesClient?> = _prizeData



    init {
    getArtistas()
}

    fun getPrize(prizeId: String) {
        viewModelScope.launch {
            prizeClientUseCase.invoke(prizeId)
                .catch {
                   // _artistasLoadingState.value = LoadingStateArtist.Error(it.message ?: "Error al cargar el premio")
                }
                .collect {
                   // _artistasLoadingState.value = LoadingStateArtist.Success(it)
                    _prizeData.value = it

                }
        }
    }

    //Solicitar lista de artistas
    fun getArtistas() {
        viewModelScope.launch {
            artistListUseCase.invoke()
                .catch {
                    _artistasLoadingState.value = LoadingStateArtist.Error(it.message ?: "Error al cargar los artistas")
                }
                .collect {
                    _artistasLoadingState.value = LoadingStateArtist.Success(it)
                }
        }
    }

    //funcion  encargada de cargar el detalle de artista
    fun getArtistDetalle(artistaId: String) {
        viewModelScope.launch {
            artistDetalleUseCase.invoke(artistaId)
                .catch {
                    _artistasLoadingStateDetalle.value = LoadingStateArtist.Error(it.message ?: "Error al cargar el artista")
                }
                .collect {
                    _artistasLoadingStateDetalle.value = LoadingStateArtist.Success(it)
                }
        }
    }


    //clase cerrada para controlar el estado de la carga
    sealed class LoadingStateArtist<out T> {//clase cerrada
    object Loading : LoadingStateArtist<Nothing>()//carga
        data class Success<T>(val data: T) : LoadingStateArtist<T>()//carga exitosa
        data class Error(val errorMessage: String) : LoadingStateArtist<Nothing>()//error
    }

    //funcion encargada del button de detalle de artista
    fun onDetailsClick(artistaId:String, navController: NavController) {
        //control para ir a la pantalla de detalle
        navController.navigate(route = AppScreem.DetalleArtista.createRoute(artistaId))
    }


}