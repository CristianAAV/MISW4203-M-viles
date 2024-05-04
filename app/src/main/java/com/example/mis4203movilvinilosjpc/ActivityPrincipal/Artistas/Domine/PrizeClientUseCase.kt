package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Domine

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.Repositorio.ArtistRepository
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Network.PrizeClient
import javax.inject.Inject

class PrizeClientUseCase @Inject constructor( private val repository: ArtistRepository){

    operator fun invoke(prizeId: String) = repository.getPrizeFlow(prizeId)

}