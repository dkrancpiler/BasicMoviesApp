package com.krancpiler.basicmoviesapp.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.krancpiler.basicmoviesapp.databinding.FragmentHomeBinding
import com.krancpiler.basicmoviesapp.utility.dismissProgress
import com.krancpiler.basicmoviesapp.utility.showProgress
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
        setUpObservers()
        fetchData()
        return binding.root
    }

    private fun init() {
        showProgress()
        binding.trendingRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.searchEditText.setOnEditorActionListener{view, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
                homeViewModel.searchMovies(view.text.toString())
                true
            } else false
        }
    }

    private fun setUpObservers() {
        homeViewModel.trendingList.observe(viewLifecycleOwner, {
            if (it != null) {
                binding.trendingRecycler.adapter = TrendingAdapter(it)
                dismissProgress()
            }
            else showProgress()
        })
        homeViewModel.searchList.observe(viewLifecycleOwner, {
            if (it != null) {
                Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun fetchData() {
        homeViewModel.getTrendingMovies()
    }
}