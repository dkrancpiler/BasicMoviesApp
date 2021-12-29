package com.krancpiler.basicmoviesapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.krancpiler.basicmoviesapp.R
import com.krancpiler.basicmoviesapp.databinding.FragmentLoginBinding
import com.krancpiler.basicmoviesapp.utility.showSimpleMessageDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        setUpObservers()
        fetchData()
        init()
        return binding.root
    }

    private fun setUpObservers() {
        loginViewModel.loginSessionResponse.observe(viewLifecycleOwner, {
            if (it.success) loginViewModel.createLastingSession()
        })
        loginViewModel.lastingSessionResponse.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        })
        loginViewModel.errorMessage.observe(viewLifecycleOwner, {
            if (it != null) {
                showSimpleMessageDialog(it)
            }
        })
    }

    private fun init() {
        binding.finishLoginButton.setOnClickListener { view ->
            val username = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()
            loginViewModel.createLoginSession(username, password, loginViewModel.requestTokenResponse.value!!.request_token)
        }
    }

    private fun fetchData() {
        loginViewModel.createRequestToken()
    }
}