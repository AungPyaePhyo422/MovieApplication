package com.example.movieapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapplication.data.popular.Result
import com.example.movieapplication.databinding.ItemPopularBinding
import com.example.movieapplication.fragment.MainPageFragmentDirections
import com.example.movieapplication.repository.utility.Constant.Companion.BASE_IMAGE_URL

class PopularMovieAdapter : ListAdapter<Result, PopularMovieAdapter.MovieViewHolder>(DiffCall) {

    class MovieViewHolder(private val binding: ItemPopularBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(result : Result){
            Glide.with(itemView).load(BASE_IMAGE_URL+result.poster_path).into(binding.ivPosterItem)
            binding.tvMovieNameItem.text = result.original_title

        }
        fun movieClick(result: Result){
            itemView.setOnClickListener(View.OnClickListener {
                val action = MainPageFragmentDirections.actionFragmentMainToDetailShowFragment(result.title, result.poster_path, result.overview)
                Navigation.findNavController(binding.root).navigate(action)
            })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val getPosition = getItem(position)
        holder.bind(getPosition)
        holder.movieClick(getPosition)


    }
}

object DiffCall : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(
        oldItem: Result,
        newItem: Result
    ): Boolean {
        return  oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Result,
        newItem: Result
    ): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }

}
