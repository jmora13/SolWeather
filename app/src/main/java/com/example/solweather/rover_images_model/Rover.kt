package com.example.solweather.rover_images_model


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

@Entity
data class Rover(
    @PrimaryKey
    @JsonProperty("id")
    val rover_id: Int,
    @JsonProperty("landing_date")
    val landingDate: String,
    @JsonProperty("launch_date")
    val launchDate: String,
    @JsonProperty("name")
    val rover_name: String,
    @JsonProperty("status")
    val status: String
)