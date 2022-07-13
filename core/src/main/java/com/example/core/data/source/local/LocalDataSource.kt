package com.example.core.data.source.local

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.entity.MovieSearchEntity
import com.example.core.data.source.local.entity.TvShowSearchEntity
import com.example.core.data.source.local.entity.TvShowEntity
import com.example.core.data.source.local.room.WestflixDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val westflixDao: WestflixDao) {

    // Tv Show

    fun getAllPopularTvShow(): Flow<List<TvShowEntity>> = westflixDao.getAllPopularTvShow()

    fun insertTvShow(listTvShow: List<TvShowEntity>) = westflixDao.insertTvShow(listTvShow)

    fun getFavoriteSearchTvShow(): Flow<List<TvShowSearchEntity>> = westflixDao.getFavoriteSearchTvShow()

    fun searchTvShow(query: String): Flow<List<TvShowSearchEntity>> = westflixDao.searchTvShow(query)

    fun getFavoritePopularTvShow(): Flow<List<TvShowEntity>> = westflixDao.getFavoritePopularTvShow()

    fun setFavoritePopularTvShow(tvShow: TvShowEntity, newState: Boolean){
        tvShow.isFavorite = newState
        westflixDao.updateFavoritePopularTvShow(tvShow)
    }

    fun setFavoriteSearchTvShow(tvShow: TvShowSearchEntity, newState: Boolean){
        tvShow.isFavorite = newState
        westflixDao.updateFavoriteSearchTvShow(tvShow)
    }

    fun insertSearchTvShow(listSearchTvShow: List<TvShowSearchEntity>) = westflixDao.insertSearchTvShow(listSearchTvShow)


    // Movie

    fun getAllPopularMovie(): Flow<List<MovieEntity>> = westflixDao.getAllPopularMovie()

    fun getFavoriteSearchMovie(): Flow<List<MovieSearchEntity>> = westflixDao.getFavoriteSearchMovie()

    fun getFavoritePopularMovie(): Flow<List<MovieEntity>> = westflixDao.getFavoritePopularMovie()

    fun searchMovie(query: String): Flow<List<MovieSearchEntity>> = westflixDao.searchMovie(query)

    fun insertMovie(listMovie: List<MovieEntity>) = westflixDao.insertMovie(listMovie)

    fun insertSearchMovie(listSearchMovie: List<MovieSearchEntity>) = westflixDao.insertSearchMovie(listSearchMovie)

    fun setFavoritePopularMovie(movie: MovieEntity, newState: Boolean){
        movie.isFavorite = newState
        westflixDao.updateFavoritePopularMovie(movie)
    }

    fun setFavoriteSearchMovie(movie: MovieSearchEntity, newState: Boolean){
        movie.isFavorite = newState
        westflixDao.updateFavoriteSearchMovie(movie)
    }

}