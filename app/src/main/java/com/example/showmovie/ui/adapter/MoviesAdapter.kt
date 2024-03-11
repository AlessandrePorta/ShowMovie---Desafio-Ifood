package com.example.showmovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.showmovie.databinding.MovieListItemBinding
import com.example.showmovie.ui.model.MovieVO

class MoviesAdapter(
    private val onMovieClicked: (MovieVO) -> Unit
) :
    PagingDataAdapter<MovieVO, MoviesAdapter.MoviesViewHolder>(diffCallback) {

    override fun getItemViewType(position: Int) = MOVIES_VIEW_TYPE

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        val view = MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, onMovieClicked)
        }
    }

    inner class MoviesViewHolder(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: MovieVO,
            onMovieClicked: (MovieVO) -> Unit
        ) {
            binding.tvMovieName.text = item.title
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/original" + item.img)
                .into(binding.ivMovieImg)
            binding.root.setOnClickListener { onMovieClicked(item) }
        }

    }

    companion object {
        const val MOVIES_VIEW_TYPE = 1

        private val diffCallback = object : DiffUtil.ItemCallback<MovieVO>() {
            override fun areItemsTheSame(oldItem: MovieVO, newItem: MovieVO): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: MovieVO, newItem: MovieVO): Boolean {
                return oldItem == newItem
            }
        }
    }
}