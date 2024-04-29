package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Core.di

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.network.AlbumClient
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.network.AlbumsListClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    //inyectamos retrofit
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.10.19:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    //Inyectamos las interface de los endpoints albums

    @Provides
    fun provideAlbumsListClient(retrofit: Retrofit): AlbumsListClient {
        return retrofit.create(AlbumsListClient::class.java)
    }
    //Inyectamos las interface de los endpoints album

    @Provides
    fun provideAlbumListClient(retrofit: Retrofit): AlbumClient {
        return retrofit.create(AlbumClient::class.java)
    }


}
