package com.example.solweather.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RemoteKeys(
        @PrimaryKey
        val photoId: Int,
        val prevKey: Int?,
        val nextKey: Int?
)