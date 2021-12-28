package com.krancpiler.basicmoviesapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krancpiler.basicmoviesapp.data.network.models.MovieModel
import com.krancpiler.basicmoviesapp.data.network.repo.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
): ViewModel() {

    val trendingList = MutableLiveData<ArrayList<MovieModel>>()
    val searchList = MutableLiveData<ArrayList<MovieModel>>()

    fun getTrendingMovies () {
        viewModelScope.launch {
            val response = moviesRepository.getTrendingMovies("movie", "week")
            if (response.isSuccessful && response.body() != null) trendingList.postValue(response.body()!!.results!!)
        }
    }

    fun searchMovies (keyword: String) {
        viewModelScope.launch {
            val response = moviesRepository.searchMovies(keyword)
            if (response.isSuccessful && response.body() != null) searchList.postValue(response.body()!!.results!!)
        }
    }

}