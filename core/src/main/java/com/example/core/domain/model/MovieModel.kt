package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    val movieId: Int,
    val movieTitle: String? = null,
    val moviePoster: String? = null,
    val movieBackdrop: String? = null,
    val movieReleaseDate: String? = null,
    val movieOverview: String? = null,
    val movieVote: Float? = null,
    val isFavorite: Boolean
) : Parcelable