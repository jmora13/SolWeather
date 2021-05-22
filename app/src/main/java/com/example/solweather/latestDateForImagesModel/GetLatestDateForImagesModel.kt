package com.example.solweather.latestDateForImagesModel


import com.fasterxml.jackson.annotation.JsonProperty

data class GetLatestDateForImagesModel(
    @JsonProperty("photo_manifest")
    val photoManifest: PhotoManifest
)