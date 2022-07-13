package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.MovieListResponse
import com.example.core.data.source.remote.response.TvShowListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // TvShow
    @GET("search/tv")
    suspend fun searchTvShow(
        @Query("api_key") api_key: String,
        @Query("query") query: String,
        @Query("page") page: String
    ): TvShowListResponse

    @GET("tv/popular")
    suspend fun getPopularTvShow(
        @Query("api_key") api_key: String,
        @Query("page") page: String
    ): TvShowListResponse

    // Movie
    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") api_key: String,
        @Query("query") query: String,
        @Query("page") page: String
    ): MovieListResponse

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") api_key: String,
        @Query("page") page: String
    ): MovieListResponse

}