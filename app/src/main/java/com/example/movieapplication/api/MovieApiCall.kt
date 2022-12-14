package com.example.movieapplication.api

import com.example.movieapplication.data.forupcoming.Upcoming
import com.example.movieapplication.data.Movie_Data
import retrofit2.Response
import retrofit2.http.GET

interface MovieApiCall {

    @GET("3/movie/upcoming?api_key=c32db68479a6422fd7e60e3b20fe4954&language=en-US&page=1")
    suspend fun getMovieData() : Response<Movie_Data>

    @GET("3/tv/popular?api_key=c32db68479a6422fd7e60e3b20fe4954&language=en-US&page=1")
    suspend fun getPopularMovieData() : Response<Upcoming>

}