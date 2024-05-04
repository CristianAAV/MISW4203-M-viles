package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Domine

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.DataItemArtista
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.Repositorio.ArtistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtistDetalleUseCase @Inject constructor(
    private val artistRepository: ArtistRepository) {

    operator fun invoke(artistId: String): Flow<DataItemArtista> =
        artistRepository.getArtistDetalleFlow(artistId)
}