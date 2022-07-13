package com.example.core.data.source.repository

import com.example.core.data.source.NetworkBoundResource
import com.example.core.data.source.Resource
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.domain.model.MovieModel
import com.example.core.domain.repository.IMovieRepository
import com.example.core.utils.AppExecutors
import com.example.core.utils.MovieDataMapper
import com.example.core.utils.SearchMovieDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryMovie(
    private val dataSourceRemote: RemoteDataSource,
    private val dataSourceLocal: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun popularMovieGet(): Flow<Resource<List<MovieModel>>> =
        object :
            NetworkBoundResource<List<MovieModel>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<MovieModel>> {
                return dataSourceLocal.getAllPopularMovie().map {
                    MovieDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MovieModel>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                dataSourceRemote.popularMovieGet()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val listMovie = MovieDataMapper.mapResponsesToEntities(data)
                dataSourceLocal.insertMovie(listMovie)
            }
        }.asFlow()

    override fun getFavoriteSearchMovie(): Flow<List<MovieModel>> {
        return dataSourceLocal.getFavoriteSearchMovie().map {
            SearchMovieDataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun movieSearch(query: String): Flow<Resource<List<MovieModel>>> =
        object :
            NetworkBoundResource<List<MovieModel>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<MovieModel>> {
                return dataSourceLocal.searchMovie(query).map {
                    SearchMovieDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MovieModel>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                dataSourceRemote.movieSearch(query)

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val listSearchMovieEntity = SearchMovieDataMapper.mapResponsesToEntities(data)
                dataSourceLocal.insertSearchMovie(listSearchMovieEntity)
            }
        }.asFlow()

    override fun popularMovieFavoriteSet(movie: MovieModel, state: Boolean) {
        val movieEntity = MovieDataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO()
            .execute { dataSourceLocal.setFavoritePopularMovie(movieEntity, state) }
    }

    override fun setFavoriteSearchMovie(movie: MovieModel, state: Boolean) {
        val searchMovieEntity = SearchMovieDataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO()
            .execute { dataSourceLocal.setFavoriteSearchMovie(searchMovieEntity, state) }
    }

    override fun popularMovieFavoriteGet(): Flow<List<MovieModel>> {
        return dataSourceLocal.getFavoritePopularMovie().map {
            MovieDataMapper.mapEntitiesToDomain(it)
        }
    }

}