package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo


//DataClas para creacion de albums
data class DataItemsCreacionAlbum(
    val name: String,
    val cover: String="http",
    val releaseDate: String="1",
    val description: String,
    val genre: String,
    val recordLabel: String
)

//listado fijo de generos [Classical, Salsa, Rock, Folk]
data class DataItemsGeneros(
    val id: Int,
    val name: List<String> = listOf("Classical", "Salsa", "Rock", "Folk")
)

//listado fijo de recordLabel[Sony Music, EMI, Discos Fuentes, Elektra, Fania Records]
data class DataItemRecordlabel(
    val id: Int,
    val name:List<String> = listOf("Sony Music", "EMI", "Discos Fuentes", "Elektra", "Fania Records")
)





