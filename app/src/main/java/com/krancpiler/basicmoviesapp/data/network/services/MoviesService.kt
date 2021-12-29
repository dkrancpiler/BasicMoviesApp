package com.krancpiler.basicmoviesapp.data.network.services

import com.krancpiler.basicmoviesapp.utility.APIConstants
import com.krancpiler.basicmoviesapp.data.network.models.BasePaginatedResponse
import com.krancpiler.basicmoviesapp.data.network.models.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET(APIConstants.TRENDING_MOVIES)
    suspend fun getTrendingMovies(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String,
    ): Response<BasePaginatedResponse<ArrayList<MovieModel>>>

    @GET(APIConstants.SEARCH_MOVIES)
    suspend fun searchMovies(
        @Query("query") keyword: String
    ): Response<BasePaginatedResponse<ArrayList<MovieModel>>>

}