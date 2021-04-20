package com.example.solweather

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.solweather.api.RetrofitInstance
import com.example.solweather.rover_images_model.Photo
import com.example.solweather.rover_images_model.RoverImagesModel
import java.text.SimpleDateFormat
import java.util.*

//class NasaPagingSource(private val marsService: MarsService): PagingSource<Int, Photo>(){
//    private val _response = MutableLiveData<String>()
//    private val _properties = MutableLiveData<RoverImagesModel>()
//    override val keyReuseSupported: Boolean = true
//    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
//        val calendar = Calendar.getInstance()
//        calendar.add(Calendar.DATE, -3)
//        val todayDate: Date = (calendar.time)
//        val formatter = SimpleDateFormat("yyyy-MM-dd")
//        val today: String = formatter.format(todayDate)
//        return try {
//            // Start refresh at page 1 if undefined.
//            val nextPage = params.key ?: 1
//            val response = RetrofitInstance.imagesApi.getRoverImages(today, nextPage, "d97Ga6ZdjIX9J8nedU5Ze09TKMhTTD2CxATei6e8")
//
//            LoadResult.Page(
//                    data = response.photos,
//                    prevKey = if (nextPage == 1) null else nextPage - 1,
//                    nextKey = nextPage + 1
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//}