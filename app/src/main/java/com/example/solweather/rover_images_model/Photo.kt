package com.example.solweather.rover_images_model


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
@Entity
data class Photo(
    @JsonProperty("camera")
    @Embedded
    val camera: Camera,
    @JsonProperty("earth_date")
    val earthDate: String,
    @PrimaryKey
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("img_src")
    val imgSrc: String,
    @JsonProperty("rover")
    @Embedded
    val rover: Rover,
    @JsonProperty("sol")
    val sol: Int
)