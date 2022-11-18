package com.example.movieapplication.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapplication.data.MovieRoomDB
import com.example.movieapplication.databinding.ItemFavouriteListBinding
import com.example.movieapplication.viewmodel.MovieDbViewModel

class FavouriteMovieAdapter(private val viewModelStoreOwner: ViewModelStoreOwner) : ListAdapter<MovieRoomDB, FavouriteMovieAdapter.MyViewHolder>(DiffCallDB) {

    class MyViewHolder(private val binding: ItemFavouriteListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movieRoomDB: MovieRoomDB) {
            Glide.with(itemView).load(movieRoomDB.image).into(binding.ivPosterItem)
            binding.tvMovieNameItem.text = movieRoomDB.title
        }

        fun delete(movieRoomDB: MovieRoomDB, viewModelStoreOwner: ViewModelStoreOwner){
            val movieDbViewmodel : MovieDbViewModel = ViewModelProvider(viewModelStoreOwner).get(
                MovieDbViewModel::class.java)
            binding.ivRemoveItem.setOnClickListener(View.OnClickListener {
                movieDbViewmodel.deleteMovie(movieRoomDB)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = ItemFavouriteListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val getPosition = getItem(position)
        holder.bind(getPosition)
        holder.delete(getPosition, viewModelStoreOwner)
    }
}

object DiffCallDB : DiffUtil.ItemCallback<MovieRoomDB>() {
    override fun areItemsTheSame(oldItem: MovieRoomDB, newItem: MovieRoomDB): Boolean {
        return  oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MovieRoomDB, newItem: MovieRoomDB): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }

}