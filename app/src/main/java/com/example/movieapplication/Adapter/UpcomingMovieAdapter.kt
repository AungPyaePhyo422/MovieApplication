package com.example.movieapplication.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapplication.data.forupcoming.Result
import com.example.movieapplication.databinding.ItemUpcomingBinding
import com.example.movieapplication.fragment.MainPageFragmentDirections
import com.example.movieapplication.repository.utility.Constant.Companion.BASE_IMAGE_URL

class UpcomingMovieAdapter : ListAdapter<com.example.movieapplication.data.forupcoming.Result, UpcomingMovieAdapter.MovieViewHolder>(DiffCallUpcoming) {


    class MovieViewHolder(private val binding : ItemUpcomingBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(result: com.example.movieapplication.data.forupcoming.Result){
            Glide.with(itemView).load(BASE_IMAGE_URL+result.poster_path).into(binding.ivPosterUpcomingItem)
            binding.tvMovieNameUpcomingItem.text = result.original_name
        }
        fun movieClick(result: com.example.movieapplication.data.forupcoming.Result) {
            itemView.setOnClickListener(View.OnClickListener {
                val action = MainPageFragmentDirections.actionFragmentMainToDetailShowFragment(result.original_name, result.poster_path, result.overview)
                Navigation.findNavController(binding.root).navigate(action)
            })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemUpcomingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
        holder.movieClick(currentItem)


    }
}

object DiffCallUpcoming : DiffUtil.ItemCallback<com.example.movieapplication.data.forupcoming.Result>() {

    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }

}