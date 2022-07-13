package com.example.westflix.di

import com.example.core.domain.usecase.InteractorMovie
import com.example.core.domain.usecase.UseCaseMovie
import com.example.core.domain.usecase.InteractorTvShow
import com.example.core.domain.usecase.UseCaseTvShow
import com.example.westflix.detail.DetailFilmViewModel
import com.example.westflix.main.home.HomeViewModel
import com.example.westflix.main.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UseCaseMovie> { InteractorMovie(get()) }
    factory<UseCaseTvShow> { InteractorTvShow(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { SearchViewModel(get(), get()) }
    viewModel { DetailFilmViewModel(get(), get()) }
}