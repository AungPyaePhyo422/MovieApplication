package com.example.movieapplication.Adapter

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapplication.data.MovieRoomDB
import com.example.movieapplication.databinding.SavedFavItemBinding
import com.example.movieapplication.fragment.DetailShowFragment
import com.example.movieapplication.repository.utility.Constant.Companion.BASE_IMAGE_URL
import com.example.movieapplication.viewmodel.Movie_DB_ViewModel

class Movie_Fav_DBAdapter(private val viewModelStoreOwner: ViewModelStoreOwner) : ListAdapter<MovieRoomDB, Movie_Fav_DBAdapter.MovieViewHolder>(DiffCallDB) {


    class MovieViewHolder(private val binding : SavedFavItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movieRoomDB: MovieRoomDB){
            Glide.with(itemView).load(movieRoomDB.image).into(binding.ivPosterItem)
            binding.tvMovieNameItem.text = movieRoomDB.title
        }

        fun delete(movieRoomDB: MovieRoomDB, viewModelStoreOwner: ViewModelStoreOwner){
            val movieDbViewmodel : Movie_DB_ViewModel = ViewModelProvider(viewModelStoreOwner).get(Movie_DB_ViewModel::class.java)
            binding.ivRemove.setOnClickListener(View.OnClickListener {
                movieDbViewmodel.deleteMovie(movieRoomDB)
            })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = SavedFavItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
        holder.delete(currentItem, viewModelStoreOwner)


    }
}

object DiffCallDB : DiffUtil.ItemCallback<MovieRoomDB>() {
    override fun areItemsTheSame(oldItem: MovieRoomDB, newItem: MovieRoomDB): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MovieRoomDB, newItem: MovieRoomDB): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }


}