package com.krancpiler.basicmoviesapp.data.network.repo

import com.krancpiler.basicmoviesapp.data.network.models.LastingSessionRequest
import com.krancpiler.basicmoviesapp.data.network.models.LoginSessionRequest
import com.krancpiler.basicmoviesapp.data.network.services.AuthService
import com.krancpiler.basicmoviesapp.data.storage.dao.UserDao
import com.krancpiler.basicmoviesapp.data.storage.entities.UserModel

class AuthRepository(
    private val authService: AuthService,
    private val userDao: UserDao
) {
    suspend fun createRequestToken() = authService.createRequestToken()

    suspend fun createLoginSession(loginSessionRequest: LoginSessionRequest) = authService.createLoginSession(loginSessionRequest)

    suspend fun createLastingSession(lastingSessionRequest: LastingSessionRequest) = authService.createLastingSession(lastingSessionRequest)

    suspend fun createGuestSession() = authService.createGuestSession()

    suspend fun storeUserInfo(userModel: UserModel) = userDao.insertUser(userModel)

    suspend fun deleteUserInfo() = userDao.delete(userDao.getUser()!!)

    fun getUserInfo() = userDao.getUser()

}