package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Core.di

import android.content.Context
import androidx.room.Room
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Daos.TaskDaosAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.Daos.TaskDaosArtists
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.Daos.TaskDaosCollectors
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Data.Models.DataBaseSql.DataBaseSql
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Data.Models.DataBaseSql.DataBaseSqlArtist
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Data.Models.DataBaseSql.DataBaseSqlCollector
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class  DataBaseModule {

    @Provides
    fun provideAlbumsDao(dataBaseSql: DataBaseSql): TaskDaosAlbums {
        return dataBaseSql.albumsDao()
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext Appcontext: Context): DataBaseSql{
        return Room.databaseBuilder(
            Appcontext,DataBaseSql::class.java, "TaskDaosEntity").build()

    }


    @Provides
    fun provideArtistsDao(dataBaseSqlArtist: DataBaseSqlArtist): TaskDaosArtists{
        return dataBaseSqlArtist.ArtistsDao()
    }

    @Provides
    @Singleton
    fun provideDataBaseArtist(@ApplicationContext Appcontext: Context): DataBaseSqlArtist {
        return Room.databaseBuilder(
            Appcontext,DataBaseSqlArtist::class.java, "TaskDaosArtist").build()
    }

    @Provides
    fun provideCollectorsDao(dataBaseSqlCollector: DataBaseSqlCollector):TaskDaosCollectors{
        return dataBaseSqlCollector.CollectorsDao()
    }

    @Provides
    @Singleton
    fun provideDataBaseCollector(@ApplicationContext Appcontext: Context): DataBaseSqlCollector {
        return Room.databaseBuilder(
            Appcontext,DataBaseSqlCollector::class.java, "TaskDaosCollector").build()
    }

}