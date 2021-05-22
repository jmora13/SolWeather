package com.example.solweather

import com.example.solweather.latestDateForImagesModel.GetLatestDateForImagesModel
import com.example.solweather.rover_images_model.RoverImagesModel
import com.example.solweather.weather_data_model.WeatherDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MarsService {

    @GET(".")
    suspend fun getWeatherData(): Response<WeatherDataModel>

    @GET("api/v1/rovers/curiosity/photos")
    suspend fun getRoverImages(
            @Query("earth_date") earth_date: String,
            @Query("page") page: Int,
            @Query("api_key") api_key: String
    ): RoverImagesModel

    @GET(".")
    suspend fun getLatestImages(
        @Query("api_key") api_key: String
    ): Response<GetLatestDateForImagesModel>


}
