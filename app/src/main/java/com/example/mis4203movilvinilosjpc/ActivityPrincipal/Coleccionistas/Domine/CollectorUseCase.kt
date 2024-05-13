package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Domine

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.Repository.CollectorsRepository
import javax.inject.Inject

class CollectorUseCase @Inject constructor(
    private val repository: CollectorsRepository
){
    operator fun invoke(collectorId: String) = repository.getCollectorFlow(collectorId)
}