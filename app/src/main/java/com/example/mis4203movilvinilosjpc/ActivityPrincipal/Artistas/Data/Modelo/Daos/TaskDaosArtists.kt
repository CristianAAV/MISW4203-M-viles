package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.DataItemArtista
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDaosArtists {
    @Query("SELECT * FROM artistas")
    fun getAlArtists(): Flow<List<DataItemArtista>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtist(artistas: List<DataItemArtista>)


}