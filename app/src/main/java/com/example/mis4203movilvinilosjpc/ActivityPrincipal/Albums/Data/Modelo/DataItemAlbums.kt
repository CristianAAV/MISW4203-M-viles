package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo

import androidx.room.PrimaryKey
import java.util.Date
data class DataItemAlbums(
    @PrimaryKey
    val id:String = "",
    val name:String = "",
    val cover:String = "",
    val releaseDate: String = "" , // todo corregir el formato fecha
    val description:String = "",
    val genre:String = "",
    val recordLabel:String = "",
    val tracks:List<Track> = emptyList(),
    val performers:List<Performer> = emptyList(),
    val comments:List<Comment> = emptyList()
)

data class Track(
    val id: Int,
    val name: String,
    val duration: String
)
data class Performer(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: Date
)

data class Comment(
    val id: Int,
    val description: String,
    val rating: Int
)


