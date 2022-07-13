package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieListResponse(

    @SerializedName("success")
    val success: Boolean?,

    @SerializedName("status_message")
    val message: String?,

    @SerializedName("results")
    val listMovie: List<MovieResponse>

)