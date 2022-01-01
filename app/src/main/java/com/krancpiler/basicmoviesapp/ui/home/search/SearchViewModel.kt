package com.krancpiler.basicmoviesapp.ui.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.krancpiler.basicmoviesapp.data.network.models.MovieModel
import com.krancpiler.basicmoviesapp.data.network.repo.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
):ViewModel() {

    fun searchMovies(searchQuery: String): Flow<PagingData<MovieModel>> {
        return Pager(PagingConfig(pageSize = 20)) {
            SearchPagingSource(moviesRepository, searchQuery)
        }.flow.cachedIn(viewModelScope)
    }

}