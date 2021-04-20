package com.example.solweather.db

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.solweather.MarsService
import com.example.solweather.api.RetrofitInstance
import com.example.solweather.rover_images_model.Photo
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException
import java.text.SimpleDateFormat
import java.util.*

private const val INITIAL_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class PhotosRemoteMediator(
    private val service: MarsService,
    private val database: PhotoDatabase
) : RemoteMediator<Int, Photo>() {
    override suspend fun load(
        // 1
        loadType: LoadType,
        // 2
        state: PagingState<Int, Photo>
    ): MediatorResult {

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -3)
        val todayDate: Date = (calendar.time)
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val today: String = formatter.format(todayDate)

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: INITIAL_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                if (remoteKeys == null) {
                    // The LoadType is PREPEND so some data was loaded before,
                    // so we should have been able to get remote keys
                    // If the remoteKeys are null, then we're an invalid state and we have a bug
                    throw InvalidObjectException("Remote key and the prevKey should not be null")
                }
                // If the previous key is null, then we can't request more data
                val prevKey = remoteKeys.prevKey
                if (prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                remoteKeys.prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                if (remoteKeys == null || remoteKeys.nextKey == null) {
                    throw InvalidObjectException("Remote key should not be null for $loadType")
                }
                remoteKeys.nextKey
            }
        }

        try {
            // 1
            val response = service.getRoverImages("2021-4-4", page, "d97Ga6ZdjIX9J8nedU5Ze09TKMhTTD2CxATei6e8")
            val photos = response.photos
            val endOfPaginationReached = photos.isEmpty()
            database.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeysDao().clearRemoteKeys()
                    database.photosDao().clearPhotos()
                }
                val prevKey = if (page == INITIAL_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = photos.map {
                    RemoteKeys(photoId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                database.remoteKeysDao().insertAll(keys)
                database.photosDao().insertAll(photos)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Photo>): RemoteKeys? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
                ?.let { photo ->
                    // Get the remote keys of the last item retrieved
                    database.remoteKeysDao().remoteKeysRepoId(photo.id)
                }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Photo>): RemoteKeys? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
                ?.let { photo ->
                    // Get the remote keys of the first items retrieved
                    database.remoteKeysDao().remoteKeysRepoId(photo.id)
                }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
            state: PagingState<Int, Photo>
    ): RemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { photoId ->
                database.remoteKeysDao().remoteKeysRepoId(photoId)
            }
        }
    }
}