package com.example.solweather.di

import com.example.solweather.MarsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.Executors
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object NetworkModule {
//
//    @Provides
//    @Singleton
//    private fun provideMarsWeatherService(): MarsService {
//        return Retrofit.Builder()
//            .baseUrl("https://api.maas2.apollorion.com/")
//            .callbackExecutor(Executors.newSingleThreadExecutor())
//            .addConverterFactory(JacksonConverterFactory.create())
//            .build()
//            .create(MarsService::class.java)
//    }
//
//    @Provides
//    @Singleton
//    private fun provideMarsImageService(): MarsService {
//        return Retrofit.Builder()
//            .baseUrl("https://api.nasa.gov/mars-photos/")
//            .callbackExecutor(Executors.newSingleThreadExecutor())
//            .addConverterFactory(JacksonConverterFactory.create())
//            .build()
//            .create(MarsService::class.java)
//    }
//
//    @Provides
//    @Singleton
//    private fun provideMarsMapService(): MarsService {
//        return Retrofit.Builder()
//            .baseUrl("https://api.nasa.gov/mars-photos/api/v1/manifests/Curiosity/")
//            .callbackExecutor(Executors.newSingleThreadExecutor())
//            .addConverterFactory(JacksonConverterFactory.create())
//            .build()
//            .create(MarsService::class.java)
//    }
//
//}