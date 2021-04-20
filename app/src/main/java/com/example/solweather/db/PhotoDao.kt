package com.example.solweather.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

import com.example.solweather.rover_images_model.Photo

@Dao
interface PhotoDao {

    @Insert(onConflict = REPLACE)
    fun insertAll(photos: List<Photo>)

    @Query("SELECT * FROM Photo" )
    fun getPhotos(): PagingSource<Int, Photo>

    @Query("DELETE FROM Photo")
    suspend fun clearPhotos()

}