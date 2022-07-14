package com.example.westflix.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.core.domain.model.MovieModel
import com.example.core.domain.model.TvShowModel
import com.example.core.utils.Credentials
import com.example.westflix.R
import com.example.westflix.databinding.ActivityDetailFilmBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFilmActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TVSHOW = "extra_tvshow"
    }


    private val detailViewModel: DetailFilmViewModel by viewModel()

    private lateinit var binding: ActivityDetailFilmBinding

    private fun detailTvShow(detailTvShow: TvShowModel?) {
        detailTvShow?.let {
            binding.detailPb.visibility = View.VISIBLE
            supportActionBar?.title = detailTvShow.tvShowTitle
            Glide.with(this@DetailFilmActivity)
                .load(Credentials.BACKDROP_BASE_URL + detailTvShow.tvShowBackdrop)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.backdropDetail)
            Glide.with(this@DetailFilmActivity)
                .load(Credentials.POSTER_BASE_URL + detailTvShow.tvShowPoster)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.posterDetail)
            binding.titleDetail.text = detailTvShow.tvShowTitle
            binding.overviewDetail.text = detailTvShow.tvShowOverview
            binding.detailItemReleaseDate.text = detailTvShow.tvShowReleaseDate
            binding.detailItemRating.text = detailTvShow.tvShowVote.toString()

            var favoriteStatus = detailTvShow.isFavorite
            statusFavoriteSet(favoriteStatus)
            binding.favBtnDetail.setOnClickListener {
                favoriteStatus = !favoriteStatus
                detailViewModel.favoritePopularTvShowSet(detailTvShow, favoriteStatus)
                detailViewModel.setFavoriteSearchTvShow(detailTvShow, favoriteStatus)
                statusFavoriteSet(favoriteStatus)
            }
            binding.detailPb.visibility = View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fullScreenSet(window)

        val movieDetail = intent.getParcelableExtra<MovieModel>(EXTRA_MOVIE)
        val tvShowDetail = intent.getParcelableExtra<TvShowModel>(EXTRA_TVSHOW)

        if (movieDetail != null) {
            detailMovieShow(movieDetail)
        } else {
            detailTvShow(tvShowDetail)
        }

        binding.closeBtnDetail.setOnClickListener {
            finish()
            onBackPressed()
        }
    }

    private fun fullScreenSet(window: Window){
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }


    private fun statusFavoriteSet(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.favBtnDetail.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_solid_24))
        } else {
            binding.favBtnDetail.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24))
        }
    }


    private fun detailMovieShow(detailMovie: MovieModel?) {
        detailMovie?.let {
            binding.detailPb.visibility = View.VISIBLE
            supportActionBar?.title = detailMovie.movieTitle
            Glide.with(this@DetailFilmActivity)
                .load(Credentials.BACKDROP_BASE_URL + detailMovie.movieBackdrop)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.backdropDetail)
            Glide.with(this@DetailFilmActivity)
                .load(Credentials.Companion.POSTER_BASE_URL + detailMovie.moviePoster)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.posterDetail)
            binding.titleDetail.text = detailMovie.movieTitle
            binding.overviewDetail.text = detailMovie.movieOverview
            binding.detailItemReleaseDate.text = detailMovie.movieReleaseDate
            binding.detailItemRating.text = detailMovie.movieVote.toString()

            var favoriteStatus = detailMovie.isFavorite
            statusFavoriteSet(favoriteStatus)
            binding.favBtnDetail.setOnClickListener {
                favoriteStatus = !favoriteStatus
                detailViewModel.favoritePopularMovieSet(detailMovie, favoriteStatus)
                detailViewModel.setFavoriteSearchMovie(detailMovie, favoriteStatus)
                statusFavoriteSet(favoriteStatus)
            }
            binding.detailPb.visibility = View.GONE
        }
    }
}