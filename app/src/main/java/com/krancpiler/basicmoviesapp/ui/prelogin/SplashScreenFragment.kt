package com.krancpiler.basicmoviesapp.ui.prelogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.krancpiler.basicmoviesapp.R
import com.krancpiler.basicmoviesapp.databinding.FragmentSplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenFragment: Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding
    private val splashScreenViewModel: SplashScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        checkUserInfo()
        return binding.root
    }

    private fun checkUserInfo() {
        val userModel = splashScreenViewModel.getUserInfo()
        if (userModel != null && userModel.isAuthorized) findNavController().navigate(R.id.splashToHome)
        else findNavController().navigate(R.id.globalActionLogin)
    }
}