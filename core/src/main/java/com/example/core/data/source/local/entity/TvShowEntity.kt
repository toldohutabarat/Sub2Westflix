package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TvShow")
data class TvShowEntity(

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "tvShowId")
    var tvShowId: Int,

    @ColumnInfo(name = "tvShowTitle")
    var tvShowTitle: String? = null,

    @ColumnInfo(name = "tvShowPoster")
    var tvShowPoster: String? = null,

    @ColumnInfo(name = "tvShowBackdrop")
    var tvShowBackdrop: String? = null,

    @ColumnInfo(name = "tvShowReleaseDate")
    var tvShowReleaseDate: String? = null,

    @ColumnInfo(name = "tvShowOverview")
    var tvShowOverview: String? = null,

    @ColumnInfo(name = "tvShowVote")
    var tvShowVote: Float? = null,

    @NonNull
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean

)