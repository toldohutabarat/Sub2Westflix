package com.example.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.core.R
import com.example.core.databinding.SearchListBinding
import com.example.core.domain.model.TvShowModel
import com.example.core.utils.Credentials
import com.example.core.utils.DiffUtilsTvShow

class AdapterSearchTvShow : RecyclerView.Adapter<AdapterSearchTvShow.SearchViewHolder>() {

    private var listData = ArrayList<TvShowModel>()
    var onItemClick: ((TvShowModel) -> Unit)? = null

    override fun getItemCount(): Int = listData.size

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = SearchListBinding.bind(itemView)
        fun bind(data: TvShowModel) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(Credentials.POSTER_BASE_URL + data.tvShowPoster)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(itemPoster)
                itemTitle.text = data.tvShowTitle
                itemReleaseDate.text = data.tvShowReleaseDate
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.search_list, parent, false)
        )

    fun setData(newListData: List<TvShowModel>?) {
        if (newListData == null) return
        val diffUtilCallback = DiffUtilsTvShow(listData, newListData)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        listData.clear()
        listData.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)
    }



    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }
}