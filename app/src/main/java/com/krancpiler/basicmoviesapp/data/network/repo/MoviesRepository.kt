package com.krancpiler.basicmoviesapp.data.network.repo

import com.krancpiler.basicmoviesapp.data.network.services.MoviesService
import okhttp3.MediaType

class MoviesRepository(
    private val moviesService: MoviesService
) {
    suspend fun getTrendingMovies(mediaType: String, timeWindow: String) = moviesService.getTrendingMovies(mediaType, timeWindow)
}