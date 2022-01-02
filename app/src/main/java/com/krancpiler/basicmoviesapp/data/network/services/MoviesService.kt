package com.krancpiler.basicmoviesapp.data.network.services

import com.krancpiler.basicmoviesapp.data.network.models.BasePaginatedResponse
import com.krancpiler.basicmoviesapp.data.network.models.MovieModel
import com.krancpiler.basicmoviesapp.data.network.models.SingleMovieDetailsResponse
import com.krancpiler.basicmoviesapp.utility.APIConstants.SEARCH_MOVIES
import com.krancpiler.basicmoviesapp.utility.APIConstants.SINGLE_MOVIE
import com.krancpiler.basicmoviesapp.utility.APIConstants.TRENDING_MOVIES
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET(TRENDING_MOVIES)
    suspend fun getTrendingMovies(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String,
    ): Response<BasePaginatedResponse<ArrayList<MovieModel>>>

    @GET(SEARCH_MOVIES)
    suspend fun searchMovies(
        @Query("query") keyword: String,
        @Query("page") page: Int
    ): Response<BasePaginatedResponse<ArrayList<MovieModel>>>

    @GET(SINGLE_MOVIE)
    suspend fun getSingleMovieInfo(
        @Path ("movie_id") movieId: Int,
        @Query ("append_to_response") additionalData: String
    ) : Response<SingleMovieDetailsResponse>

}