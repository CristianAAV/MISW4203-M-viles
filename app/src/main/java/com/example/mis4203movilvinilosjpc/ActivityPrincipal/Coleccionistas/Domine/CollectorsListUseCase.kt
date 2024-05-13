package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Domine

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.DataItemCollectors
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.Repository.CollectorsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CollectorsListUseCase @Inject constructor(
    private val repository: CollectorsRepository) {

    operator fun invoke(): Flow<List<DataItemCollectors>> = repository.getCollectorsFlow()
}