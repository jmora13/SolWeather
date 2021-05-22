package com.example.solweather.Workers

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.solweather.MarsService
import com.example.solweather.api.RetrofitInstance
import com.example.solweather.dataStore
import com.example.solweather.db.PhotoDatabase
import com.example.solweather.db.PhotosRemoteMediator
import com.example.solweather.db.PhotosRepository
import com.example.solweather.rover_images_model.Photo
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import java.util.prefs.Preferences


class NetworkWorker(appContext: Context, workerParams: WorkerParameters):
    CoroutineWorker(appContext, workerParams) {


    override suspend fun doWork(): Result {
        val database: PhotoDatabase = PhotoDatabase.getInstance(applicationContext)
        val service: MarsService = RetrofitInstance.imagesApi
        getLatestDateForImagesModel()
//        getWeather()
//        getPhotos(database,service)
        return Result.success()
    }


    private suspend fun getWeather() {
            val response = try {
                RetrofitInstance.api.getWeatherData()
            } catch (e: IOException) {
                Log.d("IOEXCEPTION", e.message.toString())
            } catch (e: HttpException) {
                Log.d("HTTPEXCEPTION", e.stackTrace.toString())
            }
    }

//    @OptIn(ExperimentalPagingApi::class)
//    private fun getPhotos(database: PhotoDatabase, service: MarsService): Flow<PagingData<Photo>> {
//        val pagingSourceFactory = { database.photosDao().getPhotos() }
//        return Pager(
//            config = PagingConfig(pageSize = 25, enablePlaceholders = false),
//            remoteMediator = PhotosRemoteMediator(
//                service,
//                database,
//                context
//            ),
//            pagingSourceFactory = pagingSourceFactory
//        ).flow
//    }

    private suspend fun getLatestDateForImagesModel() {
        val response = try {
            RetrofitInstance.latestImagesApi.getLatestImages("d97Ga6ZdjIX9J8nedU5Ze09TKMhTTD2CxATei6e8").body()?.photoManifest?.maxDate
        } catch (e: IOException) {
            Log.d("IOEXCEPTION", e.message.toString())
        } catch (e: HttpException) {
            Log.d("HTTPEXCEPTION", e.stackTrace.toString())
        }
        save("maxDate", response.toString())
    }

    suspend fun save(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        applicationContext.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

}
