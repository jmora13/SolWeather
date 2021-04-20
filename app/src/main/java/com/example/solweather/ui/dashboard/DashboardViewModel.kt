package com.example.solweather.ui.dashboard

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.solweather.Resource
import com.example.solweather.api.RetrofitInstance
import com.example.solweather.db.PhotosRepository
import com.example.solweather.rover_images_model.Photo
import com.example.solweather.rover_images_model.RoverImagesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.Response

class DashboardViewModel(val repository: PhotosRepository) : ViewModel() {


    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    private val _properties = MutableLiveData<RoverImagesModel>()

    val marsImages: MutableLiveData<Resource<RoverImagesModel>> = MutableLiveData()
    var marsImagesResponse: RoverImagesModel? = null
    var page = 1;
    val properties: LiveData<RoverImagesModel>
        get() = _properties

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
//    init {
//        getMarsImages()
//    }

    val data: Flow<PagingData<Photo>> = repository.getPhotos()
        .cachedIn(viewModelScope)
    /**
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */
//    private fun getMarsRealEstateProperties() {
//        viewModelScope.launch {
//            try {
//                _properties.value = RetrofitInstance.imagesApi.getRoverImages("2021-4-3",  1, "d97Ga6ZdjIX9J8nedU5Ze09TKMhTTD2CxATei6e8")
//                _response.value = "Success: Mars properties retrieved"
//                Log.d("RESPONSE", _response.value.toString())
//            } catch (e: Exception) {
//                _response.value = "Failure: ${e.message}"
//                Log.d("RESPONSE", _response.value.toString())
//            }
//        }
//    }

//    fun getMarsImages() = viewModelScope.launch {
//        marsImages.postValue(Resource.Loading())
//        val response = repository.getPhotos("4-12-2021", page, "d97Ga6ZdjIX9J8nedU5Ze09TKMhTTD2CxATei6e8")
//        marsImages.postValue(handleMarsImagesResponse(response))
//    }

//    private fun handleMarsImagesResponse(response: Response<RoverImagesModel>) : Resource<RoverImagesModel> {
//        if(response.isSuccessful) {
//            response.body()?.let { resultResponse ->
//                page++
//                if(marsImagesResponse == null){
//                    marsImagesResponse = resultResponse
//                } else {
//                    val oldPhotos = resultResponse?.photos
//                    val newPhotos = resultResponse.photos
//                    oldPhotos?.addAll(newPhotos)
//                }
//                return Resource.Success(marsImagesResponse ?: resultResponse)
//            }
//        }
//        return Resource.Error(response.message())
//    }

//    fun savePhoto(photo: Photo) = viewModelScope.launch {
//        PhotosRepository.upsert(photo)
//    }
}