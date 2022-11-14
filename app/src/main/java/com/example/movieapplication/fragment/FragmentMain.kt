package com.example.movieapplication.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapplication.Adapter.Movie_Adapter
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentMainBinding
import com.example.movieapplication.repository.Movie_Repository
import com.example.movieapplication.viewmodel.Movie_ViewModel
import com.example.movieapplication.viewmodel.MyViewModelFactory

class FragmentMain : Fragment() {

    private lateinit var binding : FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentMainBinding.inflate(inflater, container, false)

        val movie_adapter : Movie_Adapter? = context?.let { Movie_Adapter(it) }

        val repository = Movie_Repository()
        val viewModelFactory = MyViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(Movie_ViewModel::class.java)
        viewModel.getMovieData()

        binding.rvMain.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movie_adapter
        }

        viewModel.MyMovieData.observe(viewLifecycleOwner, Observer {
            movie_adapter?.submitList(it.body()?.results)
        })

        return binding.root
    }

}