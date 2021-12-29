package com.krancpiler.basicmoviesapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krancpiler.basicmoviesapp.data.network.models.LastingSessionRequest
import com.krancpiler.basicmoviesapp.data.network.models.LastingSessionResponse
import com.krancpiler.basicmoviesapp.data.network.models.LoginSessionRequest
import com.krancpiler.basicmoviesapp.data.network.models.RequestTokenResponse
import com.krancpiler.basicmoviesapp.data.network.repo.AuthRepository
import com.krancpiler.basicmoviesapp.utility.getErrorMessageFromRequest
import com.krancpiler.basicmoviesapp.utility.getErrorMessageFromSessionRequests
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    val requestTokenResponse = MutableLiveData<RequestTokenResponse>()
    val loginSessionResponse = MutableLiveData<RequestTokenResponse>()
    val lastingSessionResponse = MutableLiveData<LastingSessionResponse>()
    val errorMessage = MutableLiveData<String>()

    fun createRequestToken() {
        viewModelScope.launch {
            val response = authRepository.createRequestToken()
            if (response.isSuccessful && response.body() != null) requestTokenResponse.postValue(response.body())
            else if (response.errorBody() != null) errorMessage.postValue(getErrorMessageFromSessionRequests(response.errorBody()!!))
        }
    }

    fun createLoginSession(username: String, password: String, requestToken:String) {
        viewModelScope.launch {
            val loginSessionRequest = LoginSessionRequest(username, password, requestToken)
            val response = authRepository.createLoginSession(loginSessionRequest)
            if (response.isSuccessful && response.body() != null) loginSessionResponse.postValue(response.body())
            else if (response.errorBody() != null) errorMessage.postValue(getErrorMessageFromSessionRequests(response.errorBody()!!))
        }
    }

    fun createLastingSession() {
        viewModelScope.launch {
            val lastingSessionRequest = LastingSessionRequest(loginSessionResponse.value!!.request_token)
            val response = authRepository.createLastingSession(lastingSessionRequest)
            if (response.isSuccessful && response.body() != null) lastingSessionResponse.postValue(response.body())
            else if (response.errorBody() != null) errorMessage.postValue(getErrorMessageFromSessionRequests(response.errorBody()!!))
        }
    }
}