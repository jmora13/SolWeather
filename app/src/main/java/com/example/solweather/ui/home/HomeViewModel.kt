package com.example.solweather.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {


    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

//    fun getWeatherData() = viewModelScope.launch {
//       val response = RetrofitInstance.api.getWeatherData()
//
//    }

    //private fun handleWeatherResponse(response: Response<WeatherDataModel>):
}