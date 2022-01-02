package com.krancpiler.basicmoviesapp.data.network.models

data class BasePaginatedResponse<T>(
    val page:Int,
    val results:T,
    val total_pages: Int,
    val total_results: Int
)
