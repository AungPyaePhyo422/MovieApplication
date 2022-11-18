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
import com.example.movieapplication.databinding.FavItemBinding
import com.example.movieapplication.fragment.FragmentMainDirections
import com.example.movieapplication.repository.utility.Constant.Companion.BASE_IMAGE_URL

class Fav_Movie_Adapter(private val context: Context) : ListAdapter<com.example.movieapplication.data.forupcoming.Result, Fav_Movie_Adapter.MyViewHolder>(DiffCallUpcoming) {

    class MyViewHolder(private val binding: FavItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(result: com.example.movieapplication.data.forupcoming.Result) {
            Glide.with(itemView).load(BASE_IMAGE_URL + result.poster_path).into(binding.ivPosterFavItem)
            binding.tvMovieNameFavItem.text = result.original_name
        }
        fun MovieonClick(result: com.example.movieapplication.data.forupcoming.Result) {
            itemView.setOnClickListener(View.OnClickListener {
                val action = FragmentMainDirections.actionFragmentMainToDetailShowFragment(result.original_name, result.poster_path, result.overview)
                Navigation.findNavController(binding.root).navigate(action)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = FavItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Fav_Movie_Adapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val getPosition = getItem(position)
        holder.bind(getPosition)
        holder.MovieonClick(getPosition)
    }
}

object DiffCallUpcoming : DiffUtil.ItemCallback<com.example.movieapplication.data.forupcoming.Result>() {
    override fun areItemsTheSame(
        oldItem: com.example.movieapplication.data.forupcoming.Result,
        newItem: com.example.movieapplication.data.forupcoming.Result
    ): Boolean {
        return  oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: com.example.movieapplication.data.forupcoming.Result,
        newItem: com.example.movieapplication.data.forupcoming.Result
    ): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }

}