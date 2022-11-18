package com.example.movieapplication.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapplication.Adapter.FavouriteMovieAdapter
import com.example.movieapplication.databinding.FragmentFavouriteListBinding
import com.example.movieapplication.viewmodel.MovieDbViewModel

class FavouriteMovieFragment : Fragment() {

    private lateinit var favouriteMoiveAdapter : FavouriteMovieAdapter
    private lateinit var movieDbViewModel: MovieDbViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentFavouriteListBinding.inflate(inflater, container, false)

        movieDbViewModel = ViewModelProvider(this)[MovieDbViewModel::class.java]
        favouriteMoiveAdapter = FavouriteMovieAdapter(this)

        binding.rvFavouriteList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favouriteMoiveAdapter
        }

        movieDbViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            Log.d("AdapterTest", it.toString())
            favouriteMoiveAdapter.submitList(it)
        })

        return binding.root
    }

}