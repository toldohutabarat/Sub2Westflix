package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.entity.MovieSearchEntity
import com.example.core.data.source.local.entity.TvShowSearchEntity
import com.example.core.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, MovieSearchEntity::class, TvShowEntity::class, TvShowSearchEntity::class], version = 1, exportSchema = false)
abstract class WestflixDatabase : RoomDatabase() {

    abstract fun westflixDao(): WestflixDao

}