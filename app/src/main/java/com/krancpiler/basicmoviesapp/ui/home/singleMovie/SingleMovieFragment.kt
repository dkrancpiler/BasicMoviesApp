package com.krancpiler.basicmoviesapp.ui.home.singleMovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.krancpiler.basicmoviesapp.databinding.FragmentSingleMovieBinding
import com.krancpiler.basicmoviesapp.utility.changeToolbarTitle
import com.krancpiler.basicmoviesapp.utility.dismissProgress
import com.krancpiler.basicmoviesapp.utility.showProgress
import com.krancpiler.basicmoviesapp.utility.showSimpleMessageDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleMovieFragment: Fragment() {

    private lateinit var binding: FragmentSingleMovieBinding
    private val singleMovieViewModel: SingleMovieViewModel by viewModels()
    val args: SingleMovieFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingleMovieBinding.inflate(inflater, container, false)
        init()
        fetchData()
        return binding.root
    }

    private fun init() {
        changeToolbarTitle(args.movieName)
        showProgress()
        singleMovieViewModel.errorMessage.observe(viewLifecycleOwner, {
            if (it != null) {
                dismissProgress()
                showSimpleMessageDialog(it)
            }
        })
        singleMovieViewModel.singleMovieDetails.observe(viewLifecycleOwner, {
            dismissProgress()
        })
    }

    private fun fetchData() {
        singleMovieViewModel.getSingleMovie(args.movieId)
    }
}