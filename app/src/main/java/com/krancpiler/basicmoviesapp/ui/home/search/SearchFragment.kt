package com.krancpiler.basicmoviesapp.ui.home.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.paging.map
import com.krancpiler.basicmoviesapp.MainNavGraphDirections
import com.krancpiler.basicmoviesapp.R
import com.krancpiler.basicmoviesapp.databinding.FragmentSearchBinding
import com.krancpiler.basicmoviesapp.utility.changeToolbarTitle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private val args: SearchFragmentArgs by navArgs()
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        init()
        fetchData()
        return binding.root
    }

    private fun init() {
        changeToolbarTitle(String.format(resources.getString(R.string.search_fragment_title, args.searchQuery)))
        searchAdapter = SearchAdapter(SearchAdapter.MovieComparator)
        searchAdapter.onItemClick = { movieModel ->
            val action =
                MainNavGraphDirections.globalActionSingleMovie(movieModel.id, movieModel.title)
            findNavController().navigate(action)
        }
//        searchAdapter.addLoadStateListener { loadState ->
//            if (loadState.append.endOfPaginationReached) {}
//            else{}
//        }
        val searchRecyclerView = binding.searchRecycler
        searchRecyclerView.adapter = searchAdapter
    }

    private fun fetchData() {
        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.searchMovies(args.searchQuery).collectLatest { searchResult ->
                searchAdapter.submitData(searchResult)
            }
        }

    }
}