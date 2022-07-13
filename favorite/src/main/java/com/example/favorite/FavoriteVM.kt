package com.example.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.UseCaseMovie
import com.example.core.domain.usecase.UseCaseTvShow

class FavoriteVM(movieUseCase: UseCaseMovie, tvShowUseCase: UseCaseTvShow) : ViewModel() {
    val favoriteSearchMovie = movieUseCase.getFavoriteSearchMovie().asLiveData()
    val favoritePopularTvShow = tvShowUseCase.popularTvShowFavoriteGet().asLiveData()
    val favoriteSearchTvShow = tvShowUseCase.getFavoriteSearchTvShow().asLiveData()
    val favoritePopularMovie = movieUseCase.popularMovieFavoriteGet().asLiveData()

}