package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Comment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "artistas")
data class DataItemArtista(
    @PrimaryKey
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

class AlbumsConverter {
    @TypeConverter
    fun fromComments(albums: List<Album>): String {
        val gson = Gson()
        return gson.toJson(albums)
    }

    @TypeConverter
    fun toComments(AlbumsString: String): List<Album> {
        val gson = Gson()
        val type = object : TypeToken<List<Comment>>() {}.type
        return gson.fromJson(AlbumsString, type)
    }
}

class PerformerPrizesConverter {
    @TypeConverter
    fun fromComments(performerPrizes: List<PerformerPrize>): String {
        val gson = Gson()
        return gson.toJson(performerPrizes)
    }

    @TypeConverter
    fun toComments(performerPrizesString: String): List<PerformerPrize> {
        val gson = Gson()
        val type = object : TypeToken<List<PerformerPrize>>() {}.type
        return gson.fromJson(performerPrizesString, type)
    }
}

