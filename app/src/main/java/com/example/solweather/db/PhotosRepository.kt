package com.example.solweather.db

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.solweather.MarsService
import com.example.solweather.api.RetrofitInstance
import com.example.solweather.rover_images_model.Photo
import kotlinx.coroutines.flow.Flow

class PhotosRepository(
        private val service: MarsService,
        private val database: PhotoDatabase
) {

    /**
     * Search repositories whose names match the query, exposed as a stream of data that will emit
     * every time we get more data from the network.
     */
    @OptIn(ExperimentalPagingApi::class)
    fun getPhotos(): Flow<PagingData<Photo>> {
        val pagingSourceFactory = { database.photosDao().getPhotos() }
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = PhotosRemoteMediator(
                service,
                database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }

}