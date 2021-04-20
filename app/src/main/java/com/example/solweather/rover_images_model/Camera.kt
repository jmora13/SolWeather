package com.example.solweather.rover_images_model


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
@Entity
data class Camera(
    @JsonProperty("full_name")
    val fullName: String,
    @PrimaryKey
    @JsonProperty("id")
    val camera_id: Int,
    @JsonProperty("name")
    val camera_name: String,
    @JsonProperty("rover_id")
    val roverId: Int
)