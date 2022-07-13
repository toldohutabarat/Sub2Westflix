package com.example.westflix.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.UseCaseMovie
import com.example.core.domain.usecase.UseCaseTvShow

class HomeViewModel(movieUseCase: UseCaseMovie, tvShowUseCase: UseCaseTvShow) : ViewModel() {
    val moviePopular = movieUseCase.popularMovieGet().asLiveData()
    val tvShowPopular = tvShowUseCase.popularTvShowGet().asLiveData()

}