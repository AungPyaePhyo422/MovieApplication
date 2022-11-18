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
import com.example.movieapplication.Adapter.Movie_Fav_DBAdapter
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentFavouriteListBinding
import com.example.movieapplication.viewmodel.Movie_DB_ViewModel

class Favourite_List : Fragment() {

    private lateinit var adapterFl : Movie_Fav_DBAdapter
    private lateinit var movieDbViewmodel: Movie_DB_ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentFavouriteListBinding.inflate(inflater, container, false)

        movieDbViewmodel = ViewModelProvider(this).get(Movie_DB_ViewModel::class.java)
        adapterFl = Movie_Fav_DBAdapter(this)

        binding.rvFavList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = adapterFl
        }

        movieDbViewmodel.readAllData.observe(viewLifecycleOwner, Observer {
            Log.d("AdapterTest", it.toString())
            adapterFl.submitList(it)
        })


        return binding.root
    }

}