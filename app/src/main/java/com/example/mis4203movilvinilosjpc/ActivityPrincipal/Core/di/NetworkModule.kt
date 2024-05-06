package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Core.di

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.network.AlbumClient
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.network.AlbumsListClient
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Network.ArtistClient
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Network.ArtistLisClient
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Network.PrizeClient
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Network.CollectorClient
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Network.CollectorListCLient
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
            .baseUrl("http://161.132.40.204/api/")
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

    //Inyectamos las interface de los endpoints list artist
    @Provides
    fun provideArtistListClient(retrofit: Retrofit): ArtistLisClient {
        return retrofit.create(ArtistLisClient::class.java)
    }
    //Inyectamos las interface de los endpoints detalle artist
    @Provides
    fun providerArtistClient(retrofit: Retrofit): ArtistClient {
        return retrofit.create(ArtistClient::class.java)
    }

    //Inyectamos las interface de los endpoints list collectors
    @Provides
    fun providerCollectorListClient(retrofit: Retrofit): CollectorListCLient {
        return retrofit.create(CollectorListCLient::class.java)
    }

    //Inyectamos las interface de los endpoints collector detalle
    @Provides
    fun providerCollectorClient(retrofit: Retrofit): CollectorClient {
        return retrofit.create(CollectorClient::class.java)
    }

    @Provides
    fun providerPrizeClient(retrofit: Retrofit): PrizeClient {
        return retrofit.create(PrizeClient::class.java)
    }

}
