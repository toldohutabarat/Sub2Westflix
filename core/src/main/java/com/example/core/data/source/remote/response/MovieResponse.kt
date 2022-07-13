package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @SerializedName("id")
    val movieId: Int,

    @SerializedName("title")
    val movieTitle: String,

    @SerializedName("poster_path")
    val moviePoster: String,

    @SerializedName("backdrop_path")
    val movieBackdrop: String,

    @SerializedName("release_date")
    val movieReleaseDate: String,

    @SerializedName("overview")
    val movieOverview: String,

    @SerializedName("vote_average")
    val movieVote: Float,

    )