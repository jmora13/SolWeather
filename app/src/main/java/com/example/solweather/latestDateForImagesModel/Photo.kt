package com.example.solweather.latestDateForImagesModel


import com.fasterxml.jackson.annotation.JsonProperty

data class Photo(
    @JsonProperty("cameras")
    val cameras: List<String>,
    @JsonProperty("earth_date")
    val earthDate: String,
    @JsonProperty("sol")
    val sol: Int,
    @JsonProperty("total_photos")
    val totalPhotos: Int
)