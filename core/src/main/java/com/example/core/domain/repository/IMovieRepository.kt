package com.example.core.domain.repository

import com.example.core.data.source.Resource
import com.example.core.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun popularMovieFavoriteSet(movie: MovieModel, state: Boolean)

    fun popularMovieGet(): Flow<Resource<List<MovieModel>>>

    fun popularMovieFavoriteGet(): Flow<List<MovieModel>>

    fun movieSearch(query: String): Flow<Resource<List<MovieModel>>>

    fun getFavoriteSearchMovie(): Flow<List<MovieModel>>

    fun setFavoriteSearchMovie(movie: MovieModel, state: Boolean)

}