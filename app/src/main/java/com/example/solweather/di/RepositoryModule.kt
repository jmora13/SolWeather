package com.example.solweather.di

import android.content.Context
import com.example.solweather.api.RetrofitInstance
import com.example.solweather.db.PhotoDatabase
import com.example.solweather.db.PhotosRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    private fun providePhotosRepository(@ApplicationContext context: Context): PhotosRepository {
        return PhotosRepository(RetrofitInstance.imagesApi, PhotoDatabase.getInstance(context), context)
    }
}