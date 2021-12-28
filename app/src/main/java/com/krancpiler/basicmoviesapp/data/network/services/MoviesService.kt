package com.krancpiler.basicmoviesapp.data.network.services

import com.krancpiler.basicmoviesapp.data.network.APIConstants
import com.krancpiler.basicmoviesapp.data.network.models.BaseResponse
import com.krancpiler.basicmoviesapp.data.network.models.TrendingModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesService {
    @GET(APIConstants.TRENDING_MOVIES)
    suspend fun getTrendingMovies(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String,
    ): Response<BaseResponse<ArrayList<TrendingModel>>>
}