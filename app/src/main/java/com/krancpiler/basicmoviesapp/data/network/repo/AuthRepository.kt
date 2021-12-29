package com.krancpiler.basicmoviesapp.data.network.repo

import com.krancpiler.basicmoviesapp.data.network.models.LastingSessionRequest
import com.krancpiler.basicmoviesapp.data.network.models.LoginSessionRequest
import com.krancpiler.basicmoviesapp.data.network.services.AuthService

class AuthRepository(
    private val authService: AuthService
) {
    suspend fun createRequestToken() = authService.createRequestToken()

    suspend fun createLoginSession(loginSessionRequest: LoginSessionRequest) = authService.createLoginSession(loginSessionRequest)

    suspend fun createLastingSession(lastingSessionRequest: LastingSessionRequest) = authService.createLastingSession(lastingSessionRequest)

}