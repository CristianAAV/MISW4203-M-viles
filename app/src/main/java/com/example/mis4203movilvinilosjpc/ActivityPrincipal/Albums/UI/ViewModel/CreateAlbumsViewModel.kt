package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemsCreacionAlbum
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Domine.AlbumsListUseCase
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Domine.CreacionAlbumsUseCase
import com.example.mis4203movilvinilosjpc.Navigation.AppScreem
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAlbumsViewModel @Inject constructor(
    private val creacionAlbumsUseCase: CreacionAlbumsUseCase,private val albumsListUseCase: AlbumsListUseCase
) : ViewModel() {


    private val _nombreAlbum = MutableStateFlow("")
    val nombreAlbum: StateFlow<String> = _nombreAlbum

    private val _añoLanzamiento = MutableStateFlow("")
    val añoLanzamiento: StateFlow<String> = _añoLanzamiento

    private val _descriptionAlbum = MutableStateFlow("")
    val descriptionAlbum: StateFlow<String> = _descriptionAlbum

    private val _artistaCreate = MutableStateFlow("")
    val artistaCreate: StateFlow<String> = _artistaCreate

    private val _generoAlbum = MutableStateFlow("")
    val generoAlbum: StateFlow<String> = _generoAlbum

    private val _recordLabel = MutableStateFlow("")
    val recordLabel: StateFlow<String> = _recordLabel

    // LiveData para notificar cuando un álbum es creado
    private val _albumCreated = MutableLiveData<Boolean>()
    val albumCreated: LiveData<Boolean> = _albumCreated




    //Variable que controla estado del boton volver.
    private val _enableButton =
        MutableLiveData<Boolean>()
    val enableButton: LiveData<Boolean> = _enableButton

    //Variable que controla estado del boton volver.
    private val _enableButtonBackStack =
        MutableLiveData<Boolean>()
    val enableButtonBackStack: LiveData<Boolean> = _enableButtonBackStack

    //habilitar button de create album
    private val _enableButtonCreateAlbum =
        MutableLiveData<Boolean>()
    val enableButtonCreateAlbum: LiveData<Boolean> = _enableButtonCreateAlbum

    //hablita o deshabilita el boton de volver
    fun enableButton() {
        _enableButtonBackStack.value = false //deshabilita el boton de volver del detalle
        _enableButton.value = true //habilita el boton de volver del listado
        _nombreAlbum.value = ""
        _añoLanzamiento.value = ""
        _descriptionAlbum.value = ""
        _artistaCreate.value = ""
        _generoAlbum.value = ""
        _recordLabel.value = ""
        _enableButtonCreateAlbum.value = false
    }

    //habilitar button de create album si los datos estan completo
    fun onDatosChangeCreateAlbum(
        nombreAlbum: String,
        artista: String,
        año: String,
        genero: String,
        recordLabel: String,
        description: String,
    ) {

        _nombreAlbum.value = nombreAlbum
        _añoLanzamiento.value = año
        _descriptionAlbum.value = description
        _artistaCreate.value = artista
        _generoAlbum.value = genero
        _recordLabel.value = recordLabel
        isLoginEnabledCreateAlbum(nombreAlbum, año, description, genero, recordLabel, artista)

    }


    fun navegar(navController: NavController) {
        _enableButtonBackStack.value = true //deshabilita el boton de volver del detalle
        _enableButton.value = false //habilita el boton de volver del listado
        navController.navigate(AppScreem.AlbumCreate.route)

    }

    fun navegarPaginaPrincipal(navController: NavController){
        navController.navigate(AppScreem.ActivityPrincipal.route )
    }

    fun onCreateAlbum(navController: NavController) {
        viewModelScope.launch {
            creacionAlbumsUseCase.invoke(DataItemsCreacionAlbum(
                name=nombreAlbum.value,
                description = descriptionAlbum.value,
                genre = generoAlbum.value,
                recordLabel = recordLabel.value))


        }

        _albumCreated.value = true //carga exitosa
        _nombreAlbum.value = ""
        _añoLanzamiento.value = ""
        _descriptionAlbum.value = ""
        _artistaCreate.value = ""
        _generoAlbum.value = ""
        _recordLabel.value = ""
        navegarPaginaPrincipal(navController)
    }

    fun isLoginEnabledCreateAlbum(
        nombreAlbum: String,
        año: String,
        description: String,
        genero: String,
        recordLabel: String,
        artista: String,
    ) {

        if (nombreAlbum.isEmpty() ||
            año.isEmpty() ||
            description.isEmpty() ||
            genero.isEmpty() ||
            recordLabel.isEmpty() ||
            artista.isEmpty()) {

            _enableButtonCreateAlbum.value = false
        } else {
            _enableButtonCreateAlbum.value = true
        }

    }

    fun resetAlbumCreatedFlag() {
        _albumCreated.value = false
    }


}