package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Data.Models.DataBaseSql

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.CommentsConverter
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Daos.TaskDaosAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.PerformersConverter
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.TracksConverter
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.AlbumsConverter
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.Daos.TaskDaosArtists
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.DataItemArtista
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.PerformerPrizesConverter
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.ColletorAlbumsCollectorsConverter
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.CommentsCollectorConverter
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.Daos.TaskDaosCollectors
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.DataItemCollectors
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.FavoritePerformer
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.FavoritePerformerCollectorsConverter

@Database(entities =[DataItemAlbums :: class], version = 1, exportSchema = false)
@TypeConverters(TracksConverter::class, PerformersConverter::class, CommentsConverter::class)
abstract class DataBaseSql:RoomDatabase() {
    //DAOS
    abstract fun albumsDao(): TaskDaosAlbums
}

@Database(entities =[DataItemArtista :: class ], version = 1, exportSchema = false)
@TypeConverters(AlbumsConverter::class, PerformerPrizesConverter::class)
abstract class DataBaseSqlArtist:RoomDatabase() {
    //DAOS
    abstract fun ArtistsDao(): TaskDaosArtists
}

@Database(entities = [DataItemCollectors::class], version = 1, exportSchema = false)
@TypeConverters(
    CommentsCollectorConverter::class,
    FavoritePerformerCollectorsConverter::class,
    ColletorAlbumsCollectorsConverter::class)
abstract class DataBaseSqlCollector:RoomDatabase() {
    //DAOS
    abstract fun CollectorsDao(): TaskDaosCollectors
}