package com.krancpiler.basicmoviesapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krancpiler.basicmoviesapp.data.network.models.MovieModel
import com.krancpiler.basicmoviesapp.data.network.repo.MoviesRepository
import com.krancpiler.basicmoviesapp.utility.getErrorMessageFromRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
): ViewModel() {

    val trendingList = MutableLiveData<ArrayList<MovieModel>>()
    val searchList = MutableLiveData<ArrayList<MovieModel>>()
    val errorMessage = MutableLiveData<String>()

    fun getTrendingMovies () {
        viewModelScope.launch {
            val response = moviesRepository.getTrendingMovies("movie", "day")
            if (response.isSuccessful && response.body() != null) trendingList.postValue(response.body()!!.results!!)
            else if (response.errorBody() != null) errorMessage.postValue(getErrorMessageFromRequest(response.errorBody()!!))
        }
    }

    fun searchMovies (keyword: String) {
        viewModelScope.launch {
            val response = moviesRepository.searchMovies(keyword)
            if (response.isSuccessful && response.body() != null) searchList.postValue(response.body()!!.results!!)
            else if (response.errorBody() != null) errorMessage.postValue(getErrorMessageFromRequest(response.errorBody()!!))
        }
    }

}