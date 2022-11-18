package com.example.movieapplication.data.forupcoming

data class Upcoming(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)