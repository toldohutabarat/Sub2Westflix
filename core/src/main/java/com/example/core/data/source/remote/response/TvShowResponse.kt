package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

    @SerializedName("id")
    val tvShowId: Int,

    @SerializedName("name")
    val tvShowTitle: String,

    @SerializedName("poster_path")
    val tvShowPoster: String,

    @SerializedName("backdrop_path")
    val tvShowBackdrop: String,

    @SerializedName("first_air_date")
    val tvShowReleaseDate: String,

    @SerializedName("overview")
    val tvShowOverview: String,

    @SerializedName("vote_average")
    val tvShowVote: Float,

    )