package com.krancpiler.basicmoviesapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.krancpiler.basicmoviesapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        init()
        fetchData()
        setUpObservers()
        return binding.root
    }

    private fun init() {
        binding.trendingRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpObservers() {
        homeViewModel.trendingList.observe(viewLifecycleOwner, {
            binding.trendingRecycler.adapter = TrendingAdapter(it)
        })
    }

    private fun fetchData() {
        homeViewModel.getTrendingMovies()
    }
}