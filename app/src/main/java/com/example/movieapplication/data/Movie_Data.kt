package com.example.movieapplication.data

import com.example.movieapplication.data.popular.Dates
import com.example.movieapplication.data.popular.Result

data class Movie_Data(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)