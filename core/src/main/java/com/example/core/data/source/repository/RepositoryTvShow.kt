package com.example.core.data.source.repository

import com.example.core.data.source.NetworkBoundResource
import com.example.core.data.source.Resource
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.TvShowResponse
import com.example.core.domain.model.TvShowModel
import com.example.core.domain.repository.ITvShowRepository
import com.example.core.utils.AppExecutors
import com.example.core.utils.SearchTvShowDataMapper
import com.example.core.utils.TvShowDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryTvShow(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITvShowRepository {
    override fun popularTvShowGet(): Flow<Resource<List<TvShowModel>>> =
        object :
            NetworkBoundResource<List<TvShowModel>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShowModel>> {
                return localDataSource.getAllPopularTvShow().map {
                    TvShowDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShowModel>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.tvShowPopularGet()

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val listTvShow = TvShowDataMapper.mapResponsesToEntities(data)
                localDataSource.insertTvShow(listTvShow)
            }
        }.asFlow()

    override fun tvShowSearch(query: String): Flow<Resource<List<TvShowModel>>> =
        object :
            NetworkBoundResource<List<TvShowModel>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShowModel>> {
                return localDataSource.searchTvShow(query).map {
                    SearchTvShowDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShowModel>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.tvShowSearch(query)

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val listSearchMovieEntity = SearchTvShowDataMapper.mapResponsesToEntities(data)
                localDataSource.insertSearchTvShow(listSearchMovieEntity)
            }
        }.asFlow()

    override fun popularTvShowFavoriteSet(tvShow: TvShowModel, state: Boolean) {
        val tvShowEntity = TvShowDataMapper.mapDomainToEntity(tvShow)
        appExecutors.diskIO()
            .execute { localDataSource.setFavoritePopularTvShow(tvShowEntity, state) }
    }

    override fun setFavoriteSearchTvShow(tvShow: TvShowModel, state: Boolean) {
        val searchTvShowEntity = SearchTvShowDataMapper.mapDomainToEntity(tvShow)
        appExecutors.diskIO()
            .execute { localDataSource.setFavoriteSearchTvShow(searchTvShowEntity, state) }
    }

    override fun popularTvShowFavoriteGet(): Flow<List<TvShowModel>> {
        return localDataSource.getFavoritePopularTvShow().map {
            TvShowDataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavoriteSearchTvShow(): Flow<List<TvShowModel>> {
        return localDataSource.getFavoriteSearchTvShow().map {
            SearchTvShowDataMapper.mapEntitiesToDomain(it)
        }
    }
}