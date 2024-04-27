package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Core.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://161.132.40.204/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}