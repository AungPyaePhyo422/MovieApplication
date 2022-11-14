package com.example.movieapplication.api

import com.example.movieapplication.data.Movie_Data
import com.example.movieapplication.utility.Constant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : Movie_ApiCall by lazy {
        retrofit.create(Movie_ApiCall::class.java)
    }

}