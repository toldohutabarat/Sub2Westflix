package com.example.core.domain.usecase

import com.example.core.domain.model.MovieModel
import com.example.core.domain.repository.IMovieRepository

class InteractorMovie(private val movieRepository: IMovieRepository) : UseCaseMovie {

    override fun popularMovieFavoriteGet() = movieRepository.popularMovieFavoriteGet()

    override fun getFavoriteSearchMovie() = movieRepository.getFavoriteSearchMovie()

    override fun popularMovieGet() = movieRepository.popularMovieGet()

    override fun popularMovieFavoriteSet(movie: MovieModel, state: Boolean) =
        movieRepository.popularMovieFavoriteSet(movie, state)

    override fun setFavoriteSearchMovie(movie: MovieModel, state: Boolean) =
        movieRepository.setFavoriteSearchMovie(movie, state)

    override fun movieSearch(query: String) = movieRepository.movieSearch(query)

}