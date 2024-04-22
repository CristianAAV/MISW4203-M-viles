package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Domine.AlbumUseCase
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Domine.AlbumsListUseCase
import kotlinx.coroutines.launch

class AlbumsViewModel:ViewModel() {

    private val _albumsList = MutableLiveData<List<DataItemAlbums>>()
    val albumsList: LiveData<List<DataItemAlbums>> = _albumsList

    private val _album = MutableLiveData<List<DataItemAlbums>>()
    val album: LiveData<List<DataItemAlbums>> = _album

    private val _comentarios = MutableLiveData<String>()
    val comentarios: LiveData<String> = _comentarios


    val albumsListUseCase = AlbumsListUseCase()

    fun getAlbums(){
        viewModelScope.launch {
            val result = albumsListUseCase.invoke()
            _albumsList.value = result
        }
    }
    fun getAlbum(id:String){
        viewModelScope.launch {
            val result = AlbumUseCase().invoke(id)
            _album.value = listOf(result)
        }
    }



    fun onComentariosChange(it: String) {
    _comentarios.value = it
    }


}