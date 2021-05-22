package com.example.solweather.latestDateForImagesModel


import com.fasterxml.jackson.annotation.JsonProperty

data class PhotoManifest(
    @JsonProperty("landing_date")
    val landingDate: String,
    @JsonProperty("launch_date")
    val launchDate: String,
    @JsonProperty("max_date")
    val maxDate: String,
    @JsonProperty("max_sol")
    val maxSol: Int,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("photos")
    val photos: List<Photo>,
    @JsonProperty("status")
    val status: String,
    @JsonProperty("total_photos")
    val totalPhotos: Int
)