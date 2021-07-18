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

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiMarsService(): MarsService {
        return Retrofit.Builder()
            .baseUrl("https://api.maas2.apollorion.com/")
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
            .create(MarsService::class.java)
    }
}