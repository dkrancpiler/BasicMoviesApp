package com.krancpiler.basicmoviesapp.ui.prelogin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krancpiler.basicmoviesapp.data.network.models.*
import com.krancpiler.basicmoviesapp.data.network.repo.AuthRepository
import com.krancpiler.basicmoviesapp.data.storage.entities.UserModel
import com.krancpiler.basicmoviesapp.ui.BaseViewModel
import com.krancpiler.basicmoviesapp.utility.getErrorMessageFromRequests
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    val requestTokenResponse = MutableLiveData<RequestTokenResponse>()
    val loginSessionResponse = MutableLiveData<RequestTokenResponse>()
    val lastingSessionResponse = MutableLiveData<LastingSessionResponse>()
    val guestSessionResponse = MutableLiveData<GuestSessionResponse>()

    fun createRequestToken() {
        viewModelScope.launch {
            val response = authRepository.createRequestToken()
            if (response.isSuccessful && response.body() != null) requestTokenResponse.postValue(
                response.body()
            )
            else if (response.errorBody() != null) errorMessage.postValue(
                getErrorMessageFromRequests(response.errorBody()!!)
            )
        }
    }

    fun createLoginSession(username: String, password: String, requestToken: String) {
        viewModelScope.launch {
            val loginSessionRequest = LoginSessionRequest(username, password, requestToken)
            val response = authRepository.createLoginSession(loginSessionRequest)
            if (response.isSuccessful && response.body() != null) loginSessionResponse.postValue(
                response.body()
            )
            else if (response.errorBody() != null) errorMessage.postValue(
                getErrorMessageFromRequests(response.errorBody()!!)
            )
        }
    }

    fun createLastingSession() {
        viewModelScope.launch {
            val lastingSessionRequest =
                LastingSessionRequest(loginSessionResponse.value!!.request_token)
            val response = authRepository.createLastingSession(lastingSessionRequest)
            if (response.isSuccessful && response.body() != null) lastingSessionResponse.postValue(
                response.body()
            )
            else if (response.errorBody() != null) errorMessage.postValue(
                getErrorMessageFromRequests(response.errorBody()!!)
            )
        }
    }

    fun createGuestSession() {
        viewModelScope.launch {
            val response = authRepository.createGuestSession()
            if (response.isSuccessful && response.body() != null) guestSessionResponse.postValue(
                response.body()
            )
            else if (response.errorBody() != null) errorMessage.postValue(
                getErrorMessageFromRequests(response.errorBody()!!)
            )
        }
    }

    fun storeUserInfo(userModel: UserModel) {
        viewModelScope.launch {
            authRepository.storeUserInfo(userModel)
        }
    }
}