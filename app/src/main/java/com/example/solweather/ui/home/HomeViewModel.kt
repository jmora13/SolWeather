package com.example.solweather.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.solweather.api.RetrofitInstance
import com.example.solweather.weather_data_model.WeatherDataModel
import retrofit2.HttpException
import java.io.IOException

class HomeViewModel : ViewModel() {

    suspend fun getWeatherResponse(): WeatherDataModel?{
        return RetrofitInstance.api.getWeatherData().body()
    }


}