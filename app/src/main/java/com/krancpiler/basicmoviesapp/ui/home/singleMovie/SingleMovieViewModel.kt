package com.krancpiler.basicmoviesapp.ui.home.singleMovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krancpiler.basicmoviesapp.data.network.models.SingleMovieDetails
import com.krancpiler.basicmoviesapp.data.network.repo.MoviesRepository
import com.krancpiler.basicmoviesapp.utility.getErrorMessageFromRequests
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleMovieViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
): ViewModel() {

    val singleMovieDetails = MutableLiveData<SingleMovieDetails>()
    val errorMessage = MutableLiveData<String>()

    fun getSingleMovie(movieId: Int) {
        viewModelScope.launch {
            val response = moviesRepository.getSingleMovie(movieId)
            if (response.isSuccessful && response.body() != null) singleMovieDetails.postValue(response.body()!!)
            else if (response.errorBody() != null) errorMessage.postValue(getErrorMessageFromRequests(response.errorBody()!!))
        }
    }
}