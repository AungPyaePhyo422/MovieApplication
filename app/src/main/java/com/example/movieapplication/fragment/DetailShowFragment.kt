package com.example.movieapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentDetailShowBinding
import com.example.movieapplication.utility.Constant.Companion.BASE_IMAGE_URL

class DetailShowFragment(private val result: com.example.movieapplication.data.Result) : Fragment() {

    private lateinit var binding : FragmentDetailShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailShowBinding.inflate(inflater, container, false)


        Glide.with(binding.root).load(BASE_IMAGE_URL+result.poster_path).into(binding.ivPosterDetailFrg)
        binding.tvOverviewFrg.text = result.overview

        return binding.root
    }
}