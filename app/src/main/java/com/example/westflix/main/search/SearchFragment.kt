package com.example.westflix.main.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.source.Resource
import com.example.core.ui.AdapterSearchMovie
import com.example.core.ui.AdapterSearchTvShow
import com.example.westflix.R
import com.example.westflix.databinding.FragmentSearchBinding
import com.example.westflix.detail.DetailFilmActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private val viewModelSearch: SearchViewModel by viewModel()
    private lateinit var movieAdapterSearch: AdapterSearchMovie
    private lateinit var tvShowAdapterSearch: AdapterSearchTvShow
    private lateinit var option: String
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        option = parent?.getItemAtPosition(position).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvSearch.adapter = null
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            spinnerSet()

            movieAdapterSearch = AdapterSearchMovie()
            tvShowAdapterSearch = AdapterSearchTvShow()

            movieAdapterSearch.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailFilmActivity::class.java)
                intent.putExtra(DetailFilmActivity.EXTRA_MOVIE, selectedData)
                startActivity(intent)
            }

            tvShowAdapterSearch.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailFilmActivity::class.java)
                intent.putExtra(DetailFilmActivity.EXTRA_TVSHOW, selectedData)
                startActivity(intent)
            }

            binding.etQuery.setOnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (option == "Movie") {
                        movieSearch(binding.etQuery.text.toString())
                    } else {
                        tvShowSearch(binding.etQuery.text.toString())
                    }
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }

        }
    }

    private fun tvShowSearch(q: String){
        Log.d("SearchFragment Query", q)
        viewModelSearch.tvShowSearch(q).observe(viewLifecycleOwner) { tvShow ->
            if (tvShow != null) {
                when (tvShow) {
                    is Resource.Loading -> {
                        binding.tvErrorSearch.visibility = View.GONE
                        binding.progressBarSearch.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.tvErrorSearch.visibility = View.GONE
                        binding.progressBarSearch.visibility = View.GONE
                        tvShowAdapterSearch.setData(tvShow.data)
                    }
                    is Resource.Error -> {
                        binding.progressBarSearch.visibility = View.GONE
                        binding.tvErrorSearch.visibility = View.VISIBLE
                    }
                }
            }

        }

        with(binding.rvSearch) {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.adapter = tvShowAdapterSearch
        }

    }

    private fun spinnerSet() {
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.option,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.optionSearch.adapter = adapter
        binding.optionSearch.onItemSelectedListener = this
    }

    private fun movieSearch(q: String) {
        Log.d("SearchFragment Query", q)
        viewModelSearch.movieSearch(q).observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> {
                        binding.tvErrorSearch.visibility = View.GONE
                        binding.progressBarSearch.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.tvErrorSearch.visibility = View.GONE
                        binding.progressBarSearch.visibility = View.GONE
                        movieAdapterSearch.dataSet(movie.data)
                    }
                    is Resource.Error -> {
                        binding.progressBarSearch.visibility = View.GONE
                        binding.tvErrorSearch.visibility = View.VISIBLE
                    }
                }
            }

        }

        with(binding.rvSearch) {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.adapter = movieAdapterSearch
        }
    }
}