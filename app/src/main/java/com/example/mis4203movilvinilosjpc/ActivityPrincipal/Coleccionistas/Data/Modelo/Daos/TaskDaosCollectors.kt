package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.DataItemCollectors
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDaosCollectors {
    @Query("SELECT * FROM collectors")
    fun getCollectors(): Flow<List<DataItemCollectors>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollectors(Collector: List<DataItemCollectors>)
}