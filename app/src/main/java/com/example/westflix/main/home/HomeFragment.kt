package com.example.westflix.main.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.source.Resource
import com.example.core.ui.AdapterMovie
import com.example.core.ui.AdapterTvShow
import com.example.westflix.databinding.FragmentHomeBinding
import com.example.westflix.detail.DetailFilmActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeVM: HomeViewModel by viewModel()
    private lateinit var adapterMovie: AdapterMovie
    private lateinit var showAdapterTV: AdapterTvShow

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private fun loadTvShow() {
        homeVM.tvShowPopular.observe(viewLifecycleOwner) { tvShow ->
            if (tvShow != null) {
                when (tvShow) {
                    is Resource.Loading -> {
                        binding?.tvErrorHome?.visibility = View.GONE
                        binding?.progressBar?.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding?.tvErrorHome?.visibility = View.GONE
                        binding?.progressBar?.visibility = View.GONE
                        showAdapterTV.setData(tvShow.data)
                    }
                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.tvErrorHome?.visibility = View.VISIBLE
                    }
                }
            }
        }

        with(binding?.homeRvPopularTvshow) {
            this?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = showAdapterTV
        }
    }

    private fun movieLoad() {
        homeVM.moviePopular.observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> {
                        binding?.tvErrorHome?.visibility = View.GONE
                        binding?.progressBar?.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding?.tvErrorHome?.visibility = View.GONE
                        binding?.progressBar?.visibility = View.GONE
                        adapterMovie.dataSet(movie.data)
                    }
                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.tvErrorHome?.visibility = View.VISIBLE
                    }
                }
            }
        }

        with(binding?.moviePopularRvHome) {
            this?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = adapterMovie
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.moviePopularRvHome?.adapter = null
        binding?.homeRvPopularTvshow?.adapter = null
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            adapterMovie = AdapterMovie()
            showAdapterTV = AdapterTvShow()

            adapterMovie.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailFilmActivity::class.java)
                intent.putExtra(DetailFilmActivity.EXTRA_MOVIE, selectedData)
                startActivity(intent)
            }

            showAdapterTV.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailFilmActivity::class.java)
                intent.putExtra(DetailFilmActivity.EXTRA_TVSHOW, selectedData)
                startActivity(intent)
            }

            movieLoad()
            loadTvShow()
        }
    }
}