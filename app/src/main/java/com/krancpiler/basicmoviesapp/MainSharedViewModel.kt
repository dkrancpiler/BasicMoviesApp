package com.krancpiler.basicmoviesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krancpiler.basicmoviesapp.data.network.repo.AuthRepository
import com.krancpiler.basicmoviesapp.data.storage.entities.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainSharedViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    fun deleteUserInfo() {
        viewModelScope.launch {
            authRepository.deleteUserInfo()
        }
    }

    fun getUserInfo(): UserModel? = authRepository.getUserInfo()

}