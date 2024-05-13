package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "collectors")
data class DataItemCollectors(
    @PrimaryKey
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


class CommentsCollectorConverter {
    @TypeConverter
    fun fromCommentsCollector(comments: List<Comment>): String {
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

class FavoritePerformerCollectorsConverter {
    @TypeConverter
    fun fromCommentsCollector(favoritePerformers: List<FavoritePerformer>): String {
        val gson = Gson()
        return gson.toJson(favoritePerformers)
    }

    @TypeConverter
    fun toComments(favoritePerformers: String): List<FavoritePerformer> {
        val gson = Gson()
        val type = object : TypeToken<List<FavoritePerformer>>() {}.type
        return gson.fromJson(favoritePerformers, type)
    }
}

class ColletorAlbumsCollectorsConverter {
    @TypeConverter
    fun fromCommentsCollector(collectorAlbums: List<CollectorAlbum>): String {
        val gson = Gson()
        return gson.toJson(collectorAlbums)
    }

    @TypeConverter
    fun toComments(collectorAlbums: String): List<CollectorAlbum> {
        val gson = Gson()
        val type = object : TypeToken<List<CollectorAlbum>>() {}.type
        return gson.fromJson(collectorAlbums, type)
    }
}




