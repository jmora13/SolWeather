package com.example.solweather.ui.gallery

import android.view.View
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.solweather.BuildConfig
import com.example.solweather.api.RetrofitInstance
import com.example.solweather.dataStore
import com.example.solweather.db.PhotosRepository
import com.example.solweather.rover_images_model.Photo
import com.example.solweather.weather_data_model.WeatherDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlin.coroutines.coroutineContext

class GalleryViewModel(repository: PhotosRepository) : ViewModel() {

    private val API_KEY_GALLERY = BuildConfig.API_KEY_GALLERY
    val data: Flow<PagingData<Photo>> = repository.getPhotos()
        .cachedIn(viewModelScope)

    suspend fun getMaxDate(): String?{
        return RetrofitInstance.latestImagesApi.getLatestImages(API_KEY_GALLERY)
                .body()?.photoManifest?.maxDate


    }


}