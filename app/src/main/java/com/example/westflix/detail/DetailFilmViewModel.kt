package com.example.westflix.detail

import androidx.lifecycle.ViewModel
import com.example.core.domain.model.MovieModel
import com.example.core.domain.model.TvShowModel
import com.example.core.domain.usecase.UseCaseMovie
import com.example.core.domain.usecase.UseCaseTvShow

class DetailFilmViewModel(
    private val movieUseCase: UseCaseMovie,
    private val tvShowUseCase: UseCaseTvShow
) : ViewModel() {

    fun favoritePopularTvShowSet(tvShow: TvShowModel, newStatus: Boolean) =
        tvShowUseCase.popularTvShowFavoriteSet(tvShow, newStatus)

    fun favoritePopularMovieSet(movie: MovieModel, newStatus: Boolean) =
        movieUseCase.popularMovieFavoriteSet(movie, newStatus)

    fun setFavoriteSearchMovie(movie: MovieModel, newStatus: Boolean) =
        movieUseCase.setFavoriteSearchMovie(movie, newStatus)

    fun setFavoriteSearchTvShow(tvShow: TvShowModel, newStatus: Boolean) =
        tvShowUseCase.setFavoriteSearchTvShow(tvShow, newStatus)
}