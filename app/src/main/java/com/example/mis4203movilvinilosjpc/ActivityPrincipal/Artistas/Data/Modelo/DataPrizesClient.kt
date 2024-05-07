package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo

data class DataPrizesClient(
    val id: Int,
    val organization: String,
    val name: String,
    val description: String,
    val performerPrizes: List<PerformerPrize>
) {
    data class PerformerPrize(
        val id: Int,
        val premiationDate: String
    )
}
