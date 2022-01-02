package com.krancpiler.basicmoviesapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.krancpiler.basicmoviesapp.MainActivity
import com.krancpiler.basicmoviesapp.MainNavGraphDirections
import com.krancpiler.basicmoviesapp.R
import com.krancpiler.basicmoviesapp.databinding.FragmentHomeBinding
import com.krancpiler.basicmoviesapp.ui.BaseFragment
import com.krancpiler.basicmoviesapp.ui.BaseViewModel
import com.krancpiler.basicmoviesapp.utility.changeToolbarTitle
import com.krancpiler.basicmoviesapp.utility.dismissProgress
import com.krancpiler.basicmoviesapp.utility.showProgress
import com.krancpiler.basicmoviesapp.utility.showSimpleMessageDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun setUpErrorListening(viewModel: BaseViewModel) {
        homeViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        init()
        setUpObservers()
        fetchData()
        return binding.root
    }

    private fun init() {
        val activity = activity as MainActivity
        activity.setToolbarVisible()
        changeToolbarTitle(resources.getString(R.string.movies))
        showProgress()
        binding.trendingRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpObservers() {
        homeViewModel.trendingList.observe(viewLifecycleOwner, {
            if (it != null) {
                val adapter = TrendingAdapter(it)
                binding.trendingRecycler.adapter = adapter
                adapter.onItemClick = {movieModel ->
                    val action = MainNavGraphDirections.globalActionSingleMovie(movieModel.id, movieModel.title)
                    findNavController().navigate(action)
                }
                dismissProgress()
            }
        })
    }

    private fun fetchData() {
        homeViewModel.getTrendingMovies()
    }
}