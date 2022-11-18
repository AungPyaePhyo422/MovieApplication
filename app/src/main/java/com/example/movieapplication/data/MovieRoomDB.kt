package com.example.movieapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_db")
data class MovieRoomDB(

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title: String,
    val image : String

)
