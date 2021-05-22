package com.example.solweather.ui.dashboard

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.solweather.db.PhotosRepository
import com.example.solweather.rover_images_model.Photo
import com.example.solweather.rover_images_model.RoverImagesModel
import kotlinx.coroutines.flow.Flow

class DashboardViewModel(val repository: PhotosRepository) : ViewModel() {


    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    private val _properties = MutableLiveData<RoverImagesModel>()


    var marsImagesResponse: RoverImagesModel? = null
    var page = 1;
    val properties: LiveData<RoverImagesModel>
        get() = _properties

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response



    val data: Flow<PagingData<Photo>> = repository.getPhotos()
        .cachedIn(viewModelScope)

}