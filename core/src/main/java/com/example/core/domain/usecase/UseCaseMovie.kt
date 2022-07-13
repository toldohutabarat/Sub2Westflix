package com.example.core.domain.usecase

import com.example.core.data.source.Resource
import com.example.core.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface UseCaseMovie {

    fun movieSearch(query: String): Flow<Resource<List<MovieModel>>>

    fun popularMovieFavoriteGet(): Flow<List<MovieModel>>

    fun getFavoriteSearchMovie(): Flow<List<MovieModel>>

    fun popularMovieGet(): Flow<Resource<List<MovieModel>>>

    fun popularMovieFavoriteSet(movie: MovieModel, state: Boolean)

    fun setFavoriteSearchMovie(movie: MovieModel, state: Boolean)

}