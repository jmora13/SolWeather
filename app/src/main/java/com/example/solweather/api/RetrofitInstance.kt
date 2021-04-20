package com.example.solweather.api

import com.example.solweather.MarsService
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.Executors


object RetrofitInstance {

    val api: MarsService by lazy{
        Retrofit.Builder()
            .baseUrl("https://api.maas2.apollorion.com/")
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
            .create(MarsService::class.java)
    }

    val imagesApi: MarsService by lazy{
        Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/mars-photos/")
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
            .create(MarsService::class.java)
    }
}