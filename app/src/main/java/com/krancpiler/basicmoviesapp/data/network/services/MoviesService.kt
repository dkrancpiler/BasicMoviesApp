package com.krancpiler.basicmoviesapp.data.network.services

import com.krancpiler.basicmoviesapp.utility.APIConstants
import com.krancpiler.basicmoviesapp.data.network.models.BaseResponse
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
    ): Response<BaseResponse<ArrayList<MovieModel>>>

    @GET(APIConstants.SEARCH_MOVIES)
    suspend fun searchMovies(
        @Query("query") keyword: String
    ): Response<BaseResponse<ArrayList<MovieModel>>>

}