package com.krancpiler.basicmoviesapp.ui.home.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.krancpiler.basicmoviesapp.data.network.models.MovieModel
import com.krancpiler.basicmoviesapp.data.network.repo.MoviesRepository
import java.lang.Exception

class SearchPagingSource(
    private val moviesRepository: MoviesRepository,
    private val searchQuery: String
): PagingSource<Int, MovieModel>() {
    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = moviesRepository.searchMovies(searchQuery, nextPageNumber).body()!!
                LoadResult.Page(
                    data = response.results,
                    prevKey =  if(response.page == 1) null else response.page -1,
                    nextKey = if (response.results.size == 0) null else response.page + 1
                )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}