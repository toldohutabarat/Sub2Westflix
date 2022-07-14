package com.example.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.ui.AdapterMovie
import com.example.core.ui.AdapterTvShow
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.di.favoriteModule
import com.example.westflix.detail.DetailFilmActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private lateinit var tvShowAdapter: AdapterTvShow
    private lateinit var searchMovieAdapter: AdapterMovie
    private val favoriteViewModel: FavoriteVM by viewModel()
    private lateinit var searchTvShowAdapter: AdapterTvShow
    private lateinit var movieAdapter: AdapterMovie
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        loadKoinModules(favoriteModule)
        return binding?.root
    }

    private fun loadMovie() {
        favoriteViewModel.favoriteSearchMovie.observe(viewLifecycleOwner) { movie ->
            searchMovieAdapter.dataSet(movie)
        }

        favoriteViewModel.favoritePopularMovie.observe(viewLifecycleOwner) { movie ->
            movieAdapter.dataSet(movie)
        }

        with(binding?.favoriteRvFavoriteSearchMovie) {
            this?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = searchMovieAdapter
        }

        with(binding?.favoriteRvFavoriteMovie) {
            this?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = movieAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.favoriteRvFavoriteMovie?.adapter = null
        binding?.favoriteRvFavoriteTvshow?.adapter = null
        binding?.favoriteRvFavoriteSearchMovie?.adapter = null
        binding?.favoriteRvFavoriteSearchTvShow?.adapter = null
        _binding = null
    }

    private fun loadTvShow() {
        favoriteViewModel.favoriteSearchTvShow.observe(viewLifecycleOwner) { tvShow ->
            searchTvShowAdapter.setData(tvShow)
        }

        favoriteViewModel.favoritePopularTvShow.observe(viewLifecycleOwner) { tvShow ->
            tvShowAdapter.setData(tvShow)
        }

        with(binding?.favoriteRvFavoriteSearchTvShow) {
            this?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = searchTvShowAdapter
        }

        with(binding?.favoriteRvFavoriteTvshow) {
            this?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = tvShowAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            tvShowAdapter = AdapterTvShow()
            searchTvShowAdapter = AdapterTvShow()
            movieAdapter = AdapterMovie()
            searchMovieAdapter = AdapterMovie()

            searchMovieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailFilmActivity::class.java)
                intent.putExtra(DetailFilmActivity.EXTRA_MOVIE, selectedData)
                startActivity(intent)
            }

            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailFilmActivity::class.java)
                intent.putExtra(DetailFilmActivity.EXTRA_MOVIE, selectedData)
                startActivity(intent)
            }

            searchTvShowAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailFilmActivity::class.java)
                intent.putExtra(DetailFilmActivity.EXTRA_TVSHOW, selectedData)
                startActivity(intent)
            }

            tvShowAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailFilmActivity::class.java)
                intent.putExtra(DetailFilmActivity.EXTRA_TVSHOW, selectedData)
                startActivity(intent)
            }

            loadMovie()
            loadTvShow()
        }
    }
}