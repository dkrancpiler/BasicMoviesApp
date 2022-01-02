package com.krancpiler.basicmoviesapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    val errorMessage = MutableLiveData<String>()

}