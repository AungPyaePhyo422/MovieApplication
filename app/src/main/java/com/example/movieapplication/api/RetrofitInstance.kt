package com.example.movieapplication.api

import com.example.movieapplication.repository.utility.Constant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : MovieApiCall by lazy {
        retrofit.create(MovieApiCall::class.java)
    }

}