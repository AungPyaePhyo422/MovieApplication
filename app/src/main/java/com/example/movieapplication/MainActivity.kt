package com.example.movieapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapplication.Adapter.Movie_Adapter
import com.example.movieapplication.databinding.ActivityMainBinding
import com.example.movieapplication.fragment.DetailShowFragment
import com.example.movieapplication.fragment.FragmentMain
import com.example.movieapplication.repository.Movie_Repository
import com.example.movieapplication.viewmodel.Movie_ViewModel
import com.example.movieapplication.viewmodel.MyViewModelFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.FL_main, FragmentMain()).commit()

    }

    override fun navigateUpTo(upIntent: Intent?): Boolean {
        return findNavController(R.id.action_detailShowFragment_to_fragmentMain).navigateUp()
    }

}