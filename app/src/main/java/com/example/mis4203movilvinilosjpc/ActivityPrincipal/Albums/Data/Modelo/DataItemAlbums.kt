package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date
@Entity(tableName = "albums")
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

class TracksConverter {
    @TypeConverter
    fun fromTracks(tracks: List<Track>): String {
        val gson = Gson()
        return gson.toJson(tracks)
    }

    @TypeConverter
    fun toTracks(tracksString: String): List<Track> {
        val gson = Gson()
        val type = object : TypeToken<List<Track>>() {}.type
        return gson.fromJson(tracksString, type)
    }
}
class PerformersConverter {
    @TypeConverter
    fun fromPerformers(performers: List<Performer>): String {
        val gson = Gson()
        return gson.toJson(performers)
    }

    @TypeConverter
    fun toPerformers(performersString: String): List<Performer> {
        val gson = Gson()
        val type = object : TypeToken<List<Performer>>() {}.type
        return gson.fromJson(performersString, type)
    }
}

class CommentsConverter {
    @TypeConverter
    fun fromComments(comments: List<Comment>): String {
        val gson = Gson()
        return gson.toJson(comments)
    }

    @TypeConverter
    fun toComments(commentsString: String): List<Comment> {
        val gson = Gson()
        val type = object : TypeToken<List<Comment>>() {}.type
        return gson.fromJson(commentsString, type)
    }
}


