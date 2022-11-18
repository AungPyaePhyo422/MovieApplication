package com.example.movieapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.DataBase.MovieDatabase
import com.example.movieapplication.data.MovieRoomDB
import com.example.movieapplication.repository.Movie_Database_Repository
import com.example.movieapplication.repository.Movie_Repository
import kotlinx.coroutines.launch

class Movie_DB_ViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<MovieRoomDB>>
    private val repository : Movie_Database_Repository

    init {
        val userDao = MovieDatabase.getDatabase(application).movieDao()
        repository = Movie_Database_Repository(userDao)
        readAllData = repository.readAllData
    }

    fun addMovie(movieRoomDB: MovieRoomDB){
        viewModelScope.launch {
            repository.insertData(movieRoomDB)
        }
    }

    fun deleteMovie(movieRoomDB: MovieRoomDB){
        viewModelScope.launch {
            repository.deleteMovie(movieRoomDB)
        }
    }

}