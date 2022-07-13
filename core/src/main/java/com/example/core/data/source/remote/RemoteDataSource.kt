package com.example.core.data.source.remote

import android.util.Log
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.data.source.remote.response.TvShowResponse
import com.example.core.utils.Credentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    private val API_KEY = "a448ce8b16d60af78d4ae2e28cfb94a2"
    // Tv Show
    suspend fun tvShowSearch(query: String): Flow<ApiResponse<List<TvShowResponse>>> {

        return flow {
            try {
                Log.d("RemoteDataSource", query)
                val response =
                    apiService.searchTvShow(API_KEY, query, Credentials.FIRST_PAGE)
                val dataArray = response.listTvShow
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.listTvShow))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    }

    suspend fun tvShowPopularGet(): Flow<ApiResponse<List<TvShowResponse>>> {

        return flow {
            try {
                val response =
                    apiService.getPopularTvShow(API_KEY, Credentials.FIRST_PAGE)
                val dataArray = response.listTvShow
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.listTvShow))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    }

    // Movie

    suspend fun movieSearch(query: String): Flow<ApiResponse<List<MovieResponse>>> {

        return flow {
            try {
                Log.d("RemoteDataSource", query)
                val response =
                    apiService.searchMovie(API_KEY, query, Credentials.FIRST_PAGE)
                val dataArray = response.listMovie
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.listMovie))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    }

    suspend fun popularMovieGet(): Flow<ApiResponse<List<MovieResponse>>> {

        return flow {
            try {
                val response =
                    apiService.getPopularMovie(API_KEY, Credentials.FIRST_PAGE)
                val dataArray = response.listMovie
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.listMovie))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    }
}