package com.krancpiler.basicmoviesapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krancpiler.basicmoviesapp.data.network.models.TrendingModel
import com.krancpiler.basicmoviesapp.data.network.repo.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
): ViewModel() {

    val trendingList = MutableLiveData<ArrayList<TrendingModel>>()

    fun getTrendingMovies () {
        viewModelScope.launch {
            val response = moviesRepository.getTrendingMovies("movie", "week")
            if (response.isSuccessful && response.body() != null) trendingList.postValue(response.body()!!.results!!)
        }
    }

}