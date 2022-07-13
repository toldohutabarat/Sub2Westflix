package com.example.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.core.R
import com.example.core.databinding.ItemListBinding
import com.example.core.domain.model.MovieModel
import com.example.core.utils.Credentials
import com.example.core.utils.DiffUtilsMovie

class AdapterMovie : RecyclerView.Adapter<AdapterMovie.ViewHolderMovie>() {

    private var listData = ArrayList<MovieModel>()
    var onItemClick: ((MovieModel) -> Unit)? = null

    override fun getItemCount() = listData.size

    inner class ViewHolderMovie(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListBinding.bind(itemView)
        fun bind(data: MovieModel) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(Credentials.POSTER_BASE_URL + data.moviePoster)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(cardImg)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolderMovie(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )

    fun dataSet(newListData: List<MovieModel>?) {
        if (newListData == null) return
        val diffUtilCallback = DiffUtilsMovie(listData, newListData)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        listData.clear()
        listData.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: ViewHolderMovie, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

}