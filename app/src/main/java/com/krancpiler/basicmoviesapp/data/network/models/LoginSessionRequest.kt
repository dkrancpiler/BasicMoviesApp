package com.krancpiler.basicmoviesapp.data.network.models

data class LoginSessionRequest(
    private val username: String,
    private val password: String,
    private val request_token: String
)
