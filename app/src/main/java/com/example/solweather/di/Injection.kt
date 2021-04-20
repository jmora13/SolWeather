package com.example.solweather.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.solweather.MarsService
import com.example.solweather.api.RetrofitInstance
import com.example.solweather.db.PhotoDatabase
import com.example.solweather.db.PhotosRepository

object Injection {

    private fun providePhotosRepository(context: Context): PhotosRepository {
        return PhotosRepository(RetrofitInstance.imagesApi, PhotoDatabase.getInstance(context))
    }

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(providePhotosRepository(context))
    }

}