package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Domine

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Repositorio.AlbumsRepository

class AlbumUseCase {

    private val repositorio = AlbumsRepository()
    suspend operator fun invoke(album:String): DataItemAlbums {
        return repositorio.getAlbum(album)
    }
}