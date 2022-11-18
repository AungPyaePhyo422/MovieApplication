package com.example.movieapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.DataBase.MovieDatabase
import com.example.movieapplication.data.MovieRoomDB
import com.example.movieapplication.repository.MovieDatabaseRepository
import kotlinx.coroutines.launch

class MovieDbViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<MovieRoomDB>>
    private val repository : MovieDatabaseRepository

    init {
        val userDao = MovieDatabase.getDatabase(application).movieDao()
        repository = MovieDatabaseRepository(userDao)
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