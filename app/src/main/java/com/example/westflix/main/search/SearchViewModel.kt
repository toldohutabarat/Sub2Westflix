package com.example.westflix.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.source.Resource
import com.example.core.domain.model.MovieModel
import com.example.core.domain.model.TvShowModel
import com.example.core.domain.usecase.UseCaseMovie
import com.example.core.domain.usecase.UseCaseTvShow

class SearchViewModel(private val movieUseCase: UseCaseMovie, private val tvShowUseCase: UseCaseTvShow) : ViewModel() {

    fun tvShowSearch(query: String): LiveData<Resource<List<TvShowModel>>>{
        return tvShowUseCase.tvShowSearch(query).asLiveData()
    }

    fun movieSearch(query: String): LiveData<Resource<List<MovieModel>>> {
        return movieUseCase.movieSearch(query).asLiveData()
    }

}