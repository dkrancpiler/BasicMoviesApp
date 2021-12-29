package com.krancpiler.basicmoviesapp.data.network.models

data class RequestTokenResponse(
    val success: Boolean,
    val expires_at: String,
    val request_token: String
)
