package com.example.core.utils

import com.example.core.data.source.local.entity.MovieSearchEntity
import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.domain.model.MovieModel

object SearchMovieDataMapper {

    fun mapDomainToEntity(input: MovieModel) =
        MovieSearchEntity(
            movieId = input.movieId,
            movieTitle = input.movieTitle,
            moviePoster = input.moviePoster,
            movieBackdrop = input.movieBackdrop,
            movieReleaseDate = input.movieReleaseDate,
            movieOverview = input.movieOverview,
            movieVote = input.movieVote,
            isFavorite = input.isFavorite
        )

    fun mapEntitiesToDomain(input: List<MovieSearchEntity>): List<MovieModel> =
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

    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieSearchEntity> {
        val listMovie = ArrayList<MovieSearchEntity>()
        input.map {
            val movie = MovieSearchEntity(
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