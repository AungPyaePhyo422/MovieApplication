package com.example.movieapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.data.Movie_Data
import com.example.movieapplication.repository.Movie_Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class Movie_ViewModel(private val repository: Movie_Repository) : ViewModel() {

    val MyMovieData : MutableLiveData<Response<Movie_Data>> = MutableLiveData()

    fun getMovieData(){
        viewModelScope.launch {
            val mytemp = repository.getMovieData()
            MyMovieData.value = mytemp
        }
    }

}