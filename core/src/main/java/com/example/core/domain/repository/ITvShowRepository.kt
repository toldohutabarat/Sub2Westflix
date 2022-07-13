package com.example.core.domain.repository

import com.example.core.data.source.Resource
import com.example.core.domain.model.TvShowModel
import kotlinx.coroutines.flow.Flow

interface ITvShowRepository {

    fun popularTvShowFavoriteGet(): Flow<List<TvShowModel>>

    fun tvShowSearch(query: String): Flow<Resource<List<TvShowModel>>>

    fun setFavoriteSearchTvShow(tvShow: TvShowModel, state: Boolean)

    fun popularTvShowFavoriteSet(tvShow: TvShowModel, state: Boolean)

    fun popularTvShowGet(): Flow<Resource<List<TvShowModel>>>

    fun getFavoriteSearchTvShow(): Flow<List<TvShowModel>>

}