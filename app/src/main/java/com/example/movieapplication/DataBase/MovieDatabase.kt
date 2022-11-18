package com.example.movieapplication.DataBase

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapplication.MovieDao
import com.example.movieapplication.data.MovieRoomDB
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [MovieRoomDB::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao() : MovieDao

    companion object{

        @Volatile
        private var INSTANCE : MovieDatabase? = null

        fun getDatabase(context: Context) : MovieDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            kotlin.synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}