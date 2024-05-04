package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo

import androidx.room.PrimaryKey

data class DataItemArtista(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String,
    val albums: List<Album>,
    val performerPrizes: List<PerformerPrize>
)

data class Album(
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String
)

data class PerformerPrize(
    val id: Int,
    val premiationDate: String
)
