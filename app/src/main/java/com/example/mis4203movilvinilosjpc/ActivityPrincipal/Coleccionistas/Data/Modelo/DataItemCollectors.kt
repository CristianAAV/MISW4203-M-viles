package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo

data class DataItemCollectors(
    val id: Int,
    val name: String,
    val telephone: String,
    val email: String,
    val comments: List<Comment>,
    val favoritePerformers: List<FavoritePerformer>,
    val collectorAlbums: List<CollectorAlbum>
)

data class Comment(
    val id: Int,
    val description: String,
    val rating: Int
)

data class FavoritePerformer(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String
)

data class CollectorAlbum(
    val id: Int,
    val price: Int,
    val status: String
)


