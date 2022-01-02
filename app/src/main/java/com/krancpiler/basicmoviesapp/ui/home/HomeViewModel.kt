package com.krancpiler.basicmoviesapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krancpiler.basicmoviesapp.data.network.models.MovieModel
import com.krancpiler.basicmoviesapp.data.network.repo.MoviesRepository
import com.krancpiler.basicmoviesapp.ui.BaseViewModel
import com.krancpiler.basicmoviesapp.utility.getErrorMessageFromListRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
): BaseViewModel() {

    val trendingList = MutableLiveData<ArrayList<MovieModel>>()

    fun getTrendingMovies () {
        viewModelScope.launch {
            val response = moviesRepository.getTrendingMovies("movie", "day")
            if (response.isSuccessful && response.body() != null) trendingList.postValue(response.body()!!.results!!)
            else if (response.errorBody() != null) errorMessage.postValue(getErrorMessageFromListRequest(response.errorBody()!!))
        }
    }

}