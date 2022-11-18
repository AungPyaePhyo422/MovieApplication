package com.example.movieapplication.repository

import androidx.lifecycle.LiveData
import com.example.movieapplication.MovieDao
import com.example.movieapplication.data.MovieRoomDB

class Movie_Database_Repository(private val dao: MovieDao) {

    suspend fun insertData(movieRoomDB: MovieRoomDB){
        dao.insertData(movieRoomDB)
    }

    val readAllData : LiveData<List<MovieRoomDB>> = dao.readAllData()

    suspend fun deleteMovie(movieRoomDB: MovieRoomDB){
        dao.deleteMovie(movieRoomDB)
    }

}