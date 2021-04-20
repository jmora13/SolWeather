package com.example.solweather.weather_data_model


import com.fasterxml.jackson.annotation.JsonProperty

data class WeatherDataModel(
    @JsonProperty("abs_humidity")
    val absHumidity: Any?,
    @JsonProperty("atmo_opacity")
    val atmoOpacity: String,
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("local_uv_irradiance_index")
    val localUvIrradianceIndex: String,
    @JsonProperty("ls")
    val ls: Int,
    @JsonProperty("max_gts_temp")
    val maxGtsTemp: Int,
    @JsonProperty("max_temp")
    val maxTemp: Int,
    @JsonProperty("min_gts_temp")
    val minGtsTemp: Int,
    @JsonProperty("min_temp")
    val minTemp: Int,
    @JsonProperty("pressure")
    val pressure: Int,
    @JsonProperty("pressure_string")
    val pressureString: String,
    @JsonProperty("season")
    val season: String,
    @JsonProperty("sol")
    val sol: Int,
    @JsonProperty("status")
    val status: Int,
    @JsonProperty("sunrise")
    val sunrise: String,
    @JsonProperty("sunset")
    val sunset: String,
    @JsonProperty("TZ_Data")
    val tZData: String,
    @JsonProperty("terrestrial_date")
    val terrestrialDate: String,
    @JsonProperty("unitOfMeasure")
    val unitOfMeasure: String,
    @JsonProperty("wind_direction")
    val windDirection: Any?,
    @JsonProperty("wind_speed")
    val windSpeed: Any?
)