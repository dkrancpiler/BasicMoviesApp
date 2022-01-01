package com.krancpiler.basicmoviesapp.ui.prelogin

import androidx.lifecycle.ViewModel
import com.krancpiler.basicmoviesapp.data.network.repo.AuthRepository
import com.krancpiler.basicmoviesapp.data.storage.entities.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    fun getUserInfo(): UserModel? {
        return authRepository.getUserInfo()
    }


}