package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Domine

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataitemCommentsAlbum
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Repositorio.AlbumsRepository
import javax.inject.Inject

class CommentsAlbumsUseCase @Inject constructor(private val repositorio: AlbumsRepository) {
    suspend operator fun invoke(albumId: String, body: DataitemCommentsAlbum) = repositorio.postCommentsAlbums(albumId, body)
}