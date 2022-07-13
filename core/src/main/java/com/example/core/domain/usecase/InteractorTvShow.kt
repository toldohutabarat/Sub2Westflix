package com.example.core.domain.usecase

import com.example.core.domain.model.TvShowModel
import com.example.core.domain.repository.ITvShowRepository

class InteractorTvShow(private val tvShowRepository: ITvShowRepository) : UseCaseTvShow {

    override fun popularTvShowGet() = tvShowRepository.popularTvShowGet()

    override fun tvShowSearch(query: String) = tvShowRepository.tvShowSearch(query)

    override fun setFavoriteSearchTvShow(tvShow: TvShowModel, state: Boolean) =
        tvShowRepository.setFavoriteSearchTvShow(tvShow, state)

    override fun popularTvShowFavoriteGet() = tvShowRepository.popularTvShowFavoriteGet()

    override fun getFavoriteSearchTvShow() = tvShowRepository.getFavoriteSearchTvShow()

    override fun popularTvShowFavoriteSet(tvShow: TvShowModel, state: Boolean) =
        tvShowRepository.popularTvShowFavoriteSet(tvShow, state)

}