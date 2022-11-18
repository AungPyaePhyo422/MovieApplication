package com.example.movieapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.data.forupcoming.Upcoming
import com.example.movieapplication.data.Movie_Data
import com.example.movieapplication.repository.Movie_Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class Movie_ViewModel(private val repository: Movie_Repository) : ViewModel() {

    val MyMovieData : MutableLiveData<Response<Movie_Data>> = MutableLiveData()
    val MyPopularMovieData : MutableLiveData<Response<Upcoming>> = MutableLiveData()

    fun getMovieData(){
        viewModelScope.launch {
            val mytemp = repository.getMovieData()
            MyMovieData.value = mytemp
        }
    }

    fun getPopularMovieData(){
        viewModelScope.launch {
            val mytemp = repository.getPopularMovieData()
            MyPopularMovieData.value = mytemp
        }
    }

}