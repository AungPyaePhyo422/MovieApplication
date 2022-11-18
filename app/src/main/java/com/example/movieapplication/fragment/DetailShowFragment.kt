package com.example.movieapplication.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.data.MovieRoomDB
import com.example.movieapplication.databinding.FragmentDetailShowBinding
import com.example.movieapplication.repository.utility.Constant.Companion.BASE_IMAGE_URL
import com.example.movieapplication.viewmodel.MovieDbViewModel

@Suppress("DEPRECATION")
class DetailShowFragment : Fragment() {

    private lateinit var binding : FragmentDetailShowBinding
    private lateinit var movieDbViewModel: MovieDbViewModel
    private val args : DetailShowFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailShowBinding.inflate(inflater, container, false)

        Glide.with(binding.root).load(BASE_IMAGE_URL+args.url).into(binding.ivPosterDetail)
        binding.tvOverview.text = args.overview
        binding.tvTitle.text = args.name

        movieDbViewModel = ViewModelProvider(this)[MovieDbViewModel::class.java]

        setHasOptionsMenu(true)

        return binding.root
    }

     @Deprecated("Deprecated in Java")
     override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_favourite, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        when (item.itemId) {
            R.id.favourite_menu -> addData()
        }
        return false
    }

    private fun addData(){
        val movieDatabase = MovieRoomDB(0, args.name, BASE_IMAGE_URL+args.url)
        movieDbViewModel.addMovie(movieDatabase)
        Toast.makeText(context, "successfully added to db", Toast.LENGTH_SHORT).show()
    }

}