package com.krancpiler.basicmoviesapp.data.network.models

data class BaseResponse<T>(
    val page:Int,
    val results:T
)
