package com.example.core.utils

import com.example.core.data.source.local.entity.TvShowSearchEntity
import com.example.core.data.source.remote.response.TvShowResponse
import com.example.core.domain.model.TvShowModel

object SearchTvShowDataMapper {

    fun mapEntitiesToDomain(input: List<TvShowSearchEntity>): List<TvShowModel> =
        input.map {
            TvShowModel(
                tvShowId = it.tvShowId,
                tvShowTitle = it.tvShowTitle,
                tvShowPoster = it.tvShowPoster,
                tvShowBackdrop = it.tvShowBackdrop,
                tvShowReleaseDate = it.tvShowReleaseDate,
                tvShowOverview = it.tvShowOverview,
                tvShowVote = it.tvShowVote,
                isFavorite = it.isFavorite
            )
        }

    fun mapResponsesToEntities(input: List<TvShowResponse>): List<TvShowSearchEntity> {
        val listTvShow = ArrayList<TvShowSearchEntity>()
        input.map {
            val tvShow = TvShowSearchEntity(
                tvShowId = it.tvShowId,
                tvShowTitle = it.tvShowTitle,
                tvShowPoster = it.tvShowPoster,
                tvShowBackdrop = it.tvShowBackdrop,
                tvShowReleaseDate = it.tvShowReleaseDate,
                tvShowOverview = it.tvShowOverview,
                tvShowVote = it.tvShowVote,
                isFavorite = false
            )
            listTvShow.add(tvShow)
        }
        return listTvShow
    }

    fun mapDomainToEntity(input: TvShowModel) =
        TvShowSearchEntity(
            tvShowId = input.tvShowId,
            tvShowTitle = input.tvShowTitle,
            tvShowPoster = input.tvShowPoster,
            tvShowBackdrop = input.tvShowBackdrop,
            tvShowReleaseDate = input.tvShowReleaseDate,
            tvShowOverview = input.tvShowOverview,
            tvShowVote = input.tvShowVote,
            isFavorite = input.isFavorite
        )
}