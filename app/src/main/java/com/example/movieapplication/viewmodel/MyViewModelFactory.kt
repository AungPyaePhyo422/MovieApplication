package com.example.movieapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapplication.repository.Movie_Repository

class MyViewModelFactory(private val repository: Movie_Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return Movie_ViewModel(repository) as T
    }

}