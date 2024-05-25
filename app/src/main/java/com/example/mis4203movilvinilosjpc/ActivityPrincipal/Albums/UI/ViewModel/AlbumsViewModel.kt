package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataitemCommentsAlbum
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Domine.AlbumUseCase
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Domine.AlbumsListUseCase
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Domine.CommentsAlbumsUseCase
import com.example.mis4203movilvinilosjpc.Navigation.AppScreem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val albumsListUseCase: AlbumsListUseCase,
    private val albumUseCase: AlbumUseCase,
    private val commentsAlbumsUseCase: CommentsAlbumsUseCase
) : ViewModel() {

    //variable que controla el estado de lo que se carga del listado de album, privada solo para el view model
    private val _albumsLoadingState =
        MutableStateFlow<LoadingState<List<DataItemAlbums>>>(LoadingState.Loading)

    //misma variable y a la que se engancha la vista
    val albumsLoadingState: StateFlow<LoadingState<List<DataItemAlbums>>> = _albumsLoadingState

    ////variable que controla el estado de lo que se carga del detalle de album, privada solo para el view model
    private val _albumDetalleLoadingState =
        MutableStateFlow<LoadingState<DataItemAlbums>>(LoadingState.Loading)

    //misma variable y a la que se engancha la vista detalle del album
    val albumDetalleLoadingState: StateFlow<LoadingState<DataItemAlbums>> =
        _albumDetalleLoadingState

    //variable para editar comentarios
    private val _comentarios = MutableStateFlow<String>("")
    val comentarios: StateFlow<String> = _comentarios

    //variable para editar comentarios
    private val _isKeyBoardVisible = MutableStateFlow<Boolean>(false)
    val isKeyBoardVisible: StateFlow<Boolean> = _isKeyBoardVisible

    //Variable que controla estado del boton volver.
    private val _enableButton =
        MutableLiveData<Boolean>()
    val enableButton: LiveData<Boolean> = _enableButton

    //Variable que controla estado del boton volver.
    private val _enableButtonBackStack =
        MutableLiveData<Boolean>()
    val enableButtonBackStack: LiveData<Boolean> = _enableButtonBackStack

    //funcion que se encarga de cargar el listado de album
    init {//inicializador
        getAlbums()//llamada a la funcion
    }

    fun reloadAlbums() {
        getAlbums()//llamada a la funcion
    }



    //funcion que se encarga de cargar el listado de album
    fun getAlbums() {
        viewModelScope.launch {//corutina
            albumsListUseCase.invoke()//llamada a la api
                .catch {//captura de excepciones
                    _albumsLoadingState.value =//controla el estado de la carga

                        LoadingState.Error(it.message ?: "Error al cargar los álbumes")//error
                }
                .collect { albums ->//controla el estado de la carga
                    if(albums.isEmpty()){

                    }else{
                        _albumsLoadingState.value = LoadingState.Success(albums)//carga exitosa
                    }

                }
        }
    }

    //funcion que se encarga de cargar el detalle de album
    fun getAlbum(id: String) {//
        viewModelScope.launch {//corutina
            albumUseCase.invoke(id)//llamada a la api
                .catch {
                    _albumDetalleLoadingState.value =
                        LoadingState.Error(it.message ?: "Error al cargar el álbum")
                }
                .collect { album ->//controla el estado de la carga
                    _albumDetalleLoadingState.value = LoadingState.Success(album)//carga exitosa

                }
        }

    }

    // funcion para cargar los comentarios
    fun postComentarios(id: String, body: String) {
        viewModelScope.launch {
            commentsAlbumsUseCase.invoke(id, DataitemCommentsAlbum(description = body))
            _comentarios.value =""
            getAlbum(id)
            hideKeyboard()

        }
    }



    //funcion que se encarga de editar el comentario
    fun onComentariosChange(it: String) {
      _comentarios.value = it
    }

    //clase cerrada para controlar el estado de la carga
    sealed class LoadingState<out T> {
        //clase cerrada
        object Loading : LoadingState<Nothing>()//carga
        data class Success<T>(val data: T) : LoadingState<T>()//carga exitosa
        data class Error(val errorMessage: String) : LoadingState<Nothing>()//error
    }

    //funcion que se encarga de redirigir a la vista de detalle del album
    fun onDetailsClick(albumId: String, navController: NavController) {
        _enableButtonBackStack.value = true //habilita el boton de volver del detalle
        _enableButton.value = false//deshabilita el boton de volver del listado
        navController.navigate(AppScreem.DetalleAlbum.createRoute(albumId))
    }

    //hablita o deshabilita el boton de volver
    fun enableButton(){
        _enableButtonBackStack.value= false //deshabilita el boton de volver del detalle
        _enableButton.value= true //habilita el boton de volver del listado
    }

    //funcion que se encarga de formatear la fecha
    fun formatReleaseDate(releaseDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val parsedDate = inputFormat.parse(releaseDate) ?: Date()
        return outputFormat.format(parsedDate)
    }

    //funcion que se encarga de mostrar o ocultar el teclado
    fun showKeyboard() {
        _isKeyBoardVisible.value = true
    }

    //funcion que se encarga de mostrar o ocultar el teclado
    fun hideKeyboard() {
        _isKeyBoardVisible.value = false
    }

    //funcion que se encarga de cargar el listado de album
    fun addComment(id: String, comentario: String) {
        postComentarios(id,comentario)
    }
}

