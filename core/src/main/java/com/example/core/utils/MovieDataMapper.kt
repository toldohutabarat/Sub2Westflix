package com.example.core.utils

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.domain.model.MovieModel

object MovieDataMapper {

    fun mapDomainToEntity(input: MovieModel) =
        MovieEntity(
            movieId = input.movieId,
            movieTitle = input.movieTitle,
            moviePoster = input.moviePoster,
            movieBackdrop = input.movieBackdrop,
            movieReleaseDate = input.movieReleaseDate,
            movieOverview = input.movieOverview,
            movieVote = input.movieVote,
            isFavorite = input.isFavorite
        )

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<MovieModel> =
        input.map {
            MovieModel(
                movieId = it.movieId,
                movieTitle = it.movieTitle,
                moviePoster = it.moviePoster,
                movieBackdrop = it.movieBackdrop,
                movieReleaseDate = it.movieReleaseDate,
                movieOverview = it.movieOverview,
                movieVote = it.movieVote,
                isFavorite = it.isFavorite
            )
        }

    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity>{
        val listMovie = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.movieId,
                movieTitle = it.movieTitle,
                moviePoster = it.moviePoster,
                movieBackdrop = it.movieBackdrop,
                movieReleaseDate = it.movieReleaseDate,
                movieOverview = it.movieOverview,
                movieVote = it.movieVote,
                isFavorite = false
            )
            listMovie.add(movie)
        }
        return listMovie
    }
}