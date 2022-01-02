package com.krancpiler.basicmoviesapp.ui.home.singleMovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krancpiler.basicmoviesapp.data.network.models.SingleMovieDetailsResponse
import com.krancpiler.basicmoviesapp.data.network.repo.MoviesRepository
import com.krancpiler.basicmoviesapp.ui.BaseViewModel
import com.krancpiler.basicmoviesapp.utility.CustomEnums
import com.krancpiler.basicmoviesapp.utility.getErrorMessageFromRequests
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SingleMovieViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
): BaseViewModel() {

    val singleMovieDetails = MutableLiveData<SingleMovieDetailsResponse>()

    fun getSingleMovie(movieId: Int) {
        viewModelScope.launch {
            val response = moviesRepository.getSingleMovie(movieId, formAdditionalDataQuery())
            if (response.isSuccessful && response.body() != null) singleMovieDetails.postValue(response.body()!!)
            else if (response.errorBody() != null) errorMessage.postValue(getErrorMessageFromRequests(response.errorBody()!!))
        }
    }

    fun formAdditionalDataQuery() : String {
        val additionalData = CustomEnums.SingleMovieQueries.REVIEWS.name.lowercase(Locale.getDefault())
        return additionalData
    }
}