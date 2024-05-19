package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Domine

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemsCreacionAlbum
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Repositorio.AlbumsRepository
import javax.inject.Inject


class CreacionAlbumsUseCase @Inject constructor(private val repository: AlbumsRepository) {
    suspend operator fun invoke (body:DataItemsCreacionAlbum) = repository.postCreacionAlbums(body)
}