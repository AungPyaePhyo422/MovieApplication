package com.example.movieapplication.repository

import com.example.movieapplication.api.RetrofitInstance
import com.example.movieapplication.data.forupcoming.Upcoming
import com.example.movieapplication.data.Movie_Data
import retrofit2.Response

class MovieRepository {

    suspend fun getMovieData() : Response<Movie_Data>{
        return RetrofitInstance.api.getMovieData()
    }

    suspend fun getPopularMovieData() : Response<Upcoming>{
        return RetrofitInstance.api.getPopularMovieData()
    }

}