package com.example.core.domain.usecase

import com.example.core.data.source.Resource
import com.example.core.domain.model.TvShowModel
import kotlinx.coroutines.flow.Flow

interface UseCaseTvShow {

    fun popularTvShowFavoriteGet(): Flow<List<TvShowModel>>

    fun getFavoriteSearchTvShow(): Flow<List<TvShowModel>>

    fun popularTvShowGet(): Flow<Resource<List<TvShowModel>>>

    fun tvShowSearch(query: String): Flow<Resource<List<TvShowModel>>>

    fun popularTvShowFavoriteSet(tvShow: TvShowModel, state: Boolean)

    fun setFavoriteSearchTvShow(tvShow: TvShowModel, state: Boolean)

}