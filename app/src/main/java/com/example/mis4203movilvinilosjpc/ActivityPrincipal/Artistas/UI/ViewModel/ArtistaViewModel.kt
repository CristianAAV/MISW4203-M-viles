package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.UI.ViewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistaViewModel @Inject constructor(
    private val artistListUseCase: ArtistListUseCase,
    private val artistDetalleUseCase: ArtistDetalleUseCase,
    private val prizeClientUseCase: PrizeClientUseCase
):ViewModel() {

    //creamos la clase que controla el estado de la carga de la api artista
    private val _artistasLoadingState = MutableStateFlow<LoadingStateArtist<List<DataItemArtista>?>>(LoadingStateArtist.Loading)
    val artistasLoadingState: StateFlow<LoadingStateArtist<List<DataItemArtista>?>> = _artistasLoadingState

    //creamos la clase que controla el estado de la carga de la api artista detalle
    private val _artistasLoadingStateDetalle = MutableStateFlow<LoadingStateArtist<DataItemArtista?>>(LoadingStateArtist.Loading)
    val artistasLoadingStateDetalle: StateFlow<LoadingStateArtist<DataItemArtista?>> = _artistasLoadingStateDetalle

    //creamos la variable que controla los premios por artista
    private val _premiosPorArtista = MutableStateFlow<Map<Int, List<DataPrizesClient?>>>(emptyMap())
    val premiosPorArtista: StateFlow<Map<Int, List<DataPrizesClient?>>> = _premiosPorArtista


    //Variable que controla estado del boton volver.
    private val _enableButton =
        MutableLiveData<Boolean>()
    val enableButton: LiveData<Boolean> = _enableButton

    //Variable que controla estado del boton volver.
    private val _enableButtonBackStack =
        MutableLiveData<Boolean>()
    val enableButtonBackStack: LiveData<Boolean> = _enableButtonBackStack


    init {
        getArtistas()
    }
    //guardar datos de los premios por artista
    fun getPrizesForArtists(artists: List<DataItemArtista>) {
        viewModelScope.launch {
            try {
                val premiosPorArtistaMap = mutableMapOf<Int, List<DataPrizesClient>>() // Mapa para almacenar los premios por artista
                val premiosMap = mutableMapOf<Int, DataPrizesClient>() // Mapa para almacenar los premios

                // Obtener todos los premios disponibles y crear un mapeo entre su id y el objeto DataPrizesClient
                prizeClientUseCase.invoke().collect { premios ->
                    premios.forEach { premio ->//para cada premio
                        premiosMap[premio.performerPrizes.firstOrNull()?.id ?: -1] = premio //almacenar el premio
                    }
                }
                //llenamos el mapa de premios por artista
                artists.forEach { artist -> //para cada artista
                    val premios = mutableListOf<DataPrizesClient>()
                    artist.performerPrizes.forEach { performerPrize ->
                        val prize = premiosMap[performerPrize.id] // Obtener el premio correspondiente
                        if (prize != null) {
                            premios.add(prize) // Agregar el premio al listado de premios
                        }
                    }
                    premiosPorArtistaMap[artist.id] = premios //almacenar el premio por artista
                }
                _premiosPorArtista.emit(premiosPorArtistaMap)//emitir el premios por artista
            } catch (e: Exception) {
              // emitir error
                Log.e(TAG, "Error al obtener los premios por artistas", e)

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
                    //_artistasLoadingState.value = LoadingStateArtist.Success(it)
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
        _enableButtonBackStack.value = true
        _enableButton.value = false
        //control para ir a la pantalla de detalle
        navController.navigate(route = AppScreem.DetalleArtista.createRoute(artistaId))
    }

    fun enableButton(){
        _enableButtonBackStack.value= false
        _enableButton.value= true
    }

}