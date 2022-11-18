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
import com.example.movieapplication.Adapter.Fav_Movie_Adapter
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
        val movie_adapter_fav : Fav_Movie_Adapter? = context?.let { Fav_Movie_Adapter(it) }

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
            Toast.makeText(context, "${it}", Toast.LENGTH_SHORT).show()
            Log.d("Testing", it.body().toString())
        })

        viewModel.getPopularMovieData()

        binding.rvMainFav.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = movie_adapter_fav
        }

        viewModel.MyPopularMovieData.observe(viewLifecycleOwner, Observer {
            movie_adapter_fav?.submitList(it.body()?.results)
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