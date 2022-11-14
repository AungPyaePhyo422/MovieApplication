package com.example.movieapplication.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.databinding.ItemBinding
import com.example.movieapplication.fragment.DetailShowFragment
import com.example.movieapplication.fragment.FragmentMain
import com.example.movieapplication.utility.Constant.Companion.BASE_IMAGE_URL

class Movie_Adapter(private val context: Context) : ListAdapter<com.example.movieapplication.data.Result, Movie_Adapter.MovieViewHolder>(DiffCall) {

    class MovieViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(result : com.example.movieapplication.data.Result){
            Glide.with(itemView).load(BASE_IMAGE_URL+result.poster_path).into(binding.ivPosterItem)
            binding.tvMovieNameItem.text = result.original_title

        }
        fun MovieonClick(result: com.example.movieapplication.data.Result){
            itemView.setOnClickListener(View.OnClickListener {
                val fr = it!!.context as AppCompatActivity
                fr.supportFragmentManager.beginTransaction().replace(R.id.FL_main, DetailShowFragment(result), null).addToBackStack(null).commit()
            })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val getPosition = getItem(position)
        holder.bind(getPosition)
        holder.MovieonClick(getPosition)

    }
}

object DiffCall : DiffUtil.ItemCallback<com.example.movieapplication.data.Result>() {
    override fun areItemsTheSame(
        oldItem: com.example.movieapplication.data.Result,
        newItem: com.example.movieapplication.data.Result
    ): Boolean {
        return  oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: com.example.movieapplication.data.Result,
        newItem: com.example.movieapplication.data.Result
    ): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }

}
