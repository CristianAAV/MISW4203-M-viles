package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDaosAlbums {
    @Query("SELECT * FROM albums")
    fun getAlbums(): Flow<List<DataItemAlbums>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(albums: List<DataItemAlbums>)

}