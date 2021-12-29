package com.krancpiler.basicmoviesapp.data.network.services

import com.krancpiler.basicmoviesapp.data.network.models.LastingSessionRequest
import com.krancpiler.basicmoviesapp.data.network.models.LastingSessionResponse
import com.krancpiler.basicmoviesapp.data.network.models.LoginSessionRequest
import com.krancpiler.basicmoviesapp.data.network.models.RequestTokenResponse
import com.krancpiler.basicmoviesapp.utility.APIConstants.CREATE_LASTING_SESSION
import com.krancpiler.basicmoviesapp.utility.APIConstants.CREATE_REQUEST_TOKEN
import com.krancpiler.basicmoviesapp.utility.APIConstants.CREATE_SESSION_LOGIN
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @GET(CREATE_REQUEST_TOKEN)
    suspend fun createRequestToken(): Response<RequestTokenResponse>

    @POST(CREATE_SESSION_LOGIN)
    suspend fun createLoginSession(@Body loginSessionRequest:LoginSessionRequest): Response<RequestTokenResponse>

    @POST(CREATE_LASTING_SESSION)
    suspend fun createLastingSession(@Body lastingSessionRequest: LastingSessionRequest): Response<LastingSessionResponse>
}