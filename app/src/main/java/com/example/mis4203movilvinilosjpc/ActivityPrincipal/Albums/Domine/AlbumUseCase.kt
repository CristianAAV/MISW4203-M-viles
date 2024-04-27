package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Domine

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Repositorio.AlbumsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class AlbumUseCase @Inject constructor(private val repositorio : AlbumsRepository) {



    operator fun invoke(albumId:String): Flow<DataItemAlbums> {
        return repositorio.getAlbumFlow(albumId)
    }

  /*  suspend operator fun invoke(album:String): DataItemAlbums {
        return repositorio.getAlbum(album)
    }*/
}