package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Domine

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.DataItemArtista
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.Repositorio.ArtistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtistListUseCase @Inject constructor(private val repositorio : ArtistRepository) {

    operator fun invoke(): Flow<List<DataItemArtista>?> = repositorio.getArtistFlow()
}