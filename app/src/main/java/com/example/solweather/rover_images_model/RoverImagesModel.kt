package com.example.solweather.rover_images_model


import androidx.room.Entity
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty


@JsonInclude(JsonInclude.Include.NON_NULL)
data class RoverImagesModel(
    @JsonProperty("photos")
    val photos: List<Photo>
)

