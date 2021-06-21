package com.example.solweather.ui.gallery

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.solweather.db.PhotosRepository
import com.example.solweather.rover_images_model.Photo
import kotlinx.coroutines.flow.Flow

class GalleryViewModel(val repository: PhotosRepository) : ViewModel() {


    val data: Flow<PagingData<Photo>> = repository.getPhotos()
        .cachedIn(viewModelScope)

}