package com.example.core.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.example.core.domain.model.MovieModel

class DiffUtilsMovie(private val oldList: List<MovieModel>, private val newList: List<MovieModel>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].movieId == newList[newItemPosition].movieId

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (movieId1,
            movieTitle1,
            moviePoster1,
            movieBackdrop1,
            movieReleaseDate1,
            movieOverview1,
            movieVote1,
            isFavorite1) = oldList[oldItemPosition]

        val (movieId2,
            movieTitle2,
            moviePoster2,
            movieBackdrop2,
            movieReleaseDate2,
            movieOverview2,
            movieVote2,
            isFavorite2) = newList[newItemPosition]

        return movieId1 == movieId2
                && movieTitle1 == movieTitle2
                && moviePoster1 == moviePoster2
                && movieBackdrop1 == movieBackdrop2
                && movieReleaseDate1 == movieReleaseDate2
                && movieOverview1 == movieOverview2
                && movieVote1 == movieVote2
                && isFavorite1 == isFavorite2
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}