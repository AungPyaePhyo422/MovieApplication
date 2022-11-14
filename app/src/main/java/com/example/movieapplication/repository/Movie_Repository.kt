package com.example.movieapplication.repository

import com.example.movieapplication.api.RetrofitInstance
import com.example.movieapplication.data.Movie_Data
import retrofit2.Response

class Movie_Repository {

    suspend fun getMovieData() : Response<Movie_Data>{
        return RetrofitInstance.api.getMovieData()
    }

}