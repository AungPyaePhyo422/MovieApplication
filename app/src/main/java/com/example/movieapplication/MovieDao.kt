package com.example.movieapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapplication.data.MovieRoomDB

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(movieRoomDB: MovieRoomDB)

    @Query("SELECT * FROM movie_db ORDER BY id ASC")
    fun readAllData() : LiveData<List<MovieRoomDB>>

    @Delete
    suspend fun deleteMovie(movieRoomDB: MovieRoomDB)

}