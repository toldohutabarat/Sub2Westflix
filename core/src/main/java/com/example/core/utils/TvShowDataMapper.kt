package com.example.core.utils

import com.example.core.data.source.local.entity.TvShowEntity
import com.example.core.data.source.remote.response.TvShowResponse
import com.example.core.domain.model.TvShowModel

object TvShowDataMapper {

    fun mapDomainToEntity(input: TvShowModel) =
        TvShowEntity(
            tvShowId = input.tvShowId,
            tvShowTitle = input.tvShowTitle,
            tvShowPoster = input.tvShowPoster,
            tvShowBackdrop = input.tvShowBackdrop,
            tvShowReleaseDate = input.tvShowReleaseDate,
            tvShowOverview = input.tvShowOverview,
            tvShowVote = input.tvShowVote,
            isFavorite = input.isFavorite
        )

    fun mapEntitiesToDomain(input: List<TvShowEntity>): List<TvShowModel> =
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

    fun mapResponsesToEntities(input: List<TvShowResponse>): List<TvShowEntity> {
        val listTvShow = ArrayList<TvShowEntity>()
        input.map {
            val tvShow = TvShowEntity(
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
}