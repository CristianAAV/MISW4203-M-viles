package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Domine

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Repositorio.AlbumsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlbumsListUseCase @Inject constructor(private val repositorio : AlbumsRepository) {

   /*  suspend operator fun invoke(): List<DataItemAlbums> {
         return repositorio.getAlbums()
     }*/

    operator fun invoke(): Flow<List<DataItemAlbums>> = repositorio.getAlbumsFlow()


}