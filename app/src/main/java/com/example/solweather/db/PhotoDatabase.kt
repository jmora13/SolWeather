package com.example.solweather.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.solweather.rover_images_model.Camera
import com.example.solweather.rover_images_model.Photo
import com.example.solweather.rover_images_model.Rover

@Database(entities = [
    RemoteKeys::class,
    Rover::class,
    Photo::class,
    Camera::class ],
    version = 1, exportSchema = false)

abstract class PhotoDatabase : RoomDatabase() {
    abstract fun photosDao(): PhotoDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var INSTANCE: PhotoDatabase? = null
        fun getInstance(context: Context): PhotoDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                PhotoDatabase::class.java, "photo.db")
                .fallbackToDestructiveMigration()
                .build()

    }



}