package com.example.movieapplication.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapplication.Adapter.*
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentMainBinding
import com.example.movieapplication.repository.MovieRepository
import com.example.movieapplication.viewmodel.MovieViewModel
import com.example.movieapplication.viewmodel.MyViewModelFactory

class MainPageFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding
    private lateinit var upcomingMovieAdapter: UpcomingMovieAdapter
    private lateinit var popularMovieAdapter : PopularMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentMainBinding.inflate(inflater, container, false)

        upcomingMovieAdapter = UpcomingMovieAdapter()
        popularMovieAdapter = PopularMovieAdapter()

        val repository = MovieRepository()
        val viewModelFactory = MyViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[MovieViewModel::class.java]

        viewModel.getMovieData()
        viewModel.getPopularMovieData()

        binding.rvUpcomingMovie.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = upcomingMovieAdapter
        }

        viewModel.MyPopularMovieData.observe(viewLifecycleOwner, Observer {
            upcomingMovieAdapter.submitList(it.body()?.results)
        })

        binding.rvPopularMovie.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = popularMovieAdapter
        }

        viewModel.MyMovieData.observe(viewLifecycleOwner, Observer {
            popularMovieAdapter.submitList(it.body()?.results)
        })



        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_favourite, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        when(item.itemId){
            R.id.favourite_menu ->{
                Navigation.findNavController(binding.root).navigate(R.id.action_fragmentMain_to_favourite_List)
            }
        }
        return false

    }

}