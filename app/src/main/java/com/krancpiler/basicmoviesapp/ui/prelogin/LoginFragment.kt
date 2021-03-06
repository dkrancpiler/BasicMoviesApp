package com.krancpiler.basicmoviesapp.ui.prelogin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.krancpiler.basicmoviesapp.R
import com.krancpiler.basicmoviesapp.data.storage.entities.UserModel
import com.krancpiler.basicmoviesapp.databinding.FragmentLoginBinding
import com.krancpiler.basicmoviesapp.ui.BaseFragment
import com.krancpiler.basicmoviesapp.utility.dismissProgress
import com.krancpiler.basicmoviesapp.utility.showProgress
import com.krancpiler.basicmoviesapp.utility.showSimpleMessageDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

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
            else dismissProgress()
        })
        loginViewModel.lastingSessionResponse.observe(viewLifecycleOwner, {
            val userModel = UserModel(0, it.session_id, binding.usernameInput.text.toString(), true)
            proceedToHome(userModel)
        })
        loginViewModel.errorMessage.observe(viewLifecycleOwner, {
            if (it != null) {
                dismissProgress()
                showSimpleMessageDialog(it)
            }
        })
        loginViewModel.guestSessionResponse.observe(viewLifecycleOwner, {
            if (it != null) {
                val userModel = UserModel(0, it.guest_session_id, resources.getString(R.string.guest), false)
                proceedToHome(userModel)
            }
        })
    }

    private fun init() {
        binding.finishLoginButton.setOnClickListener {
            showProgress()
            val username = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()
            loginViewModel.createLoginSession(
                username,
                password,
                loginViewModel.requestTokenResponse.value!!.request_token
            )
        }
        binding.passwordInput.setOnEditorActionListener{view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
                binding.finishLoginButton.performClick()
                true
            } else false
        }
        binding.guestLoginButton.setOnClickListener {
            showProgress()
            loginViewModel.createGuestSession()
        }
    }

    private fun proceedToHome(userModel: UserModel) {
        loginViewModel.storeUserInfo(userModel)
        dismissProgress()
        findNavController().navigate(R.id.loginToHome)
    }

    private fun fetchData() {
        loginViewModel.createRequestToken()
    }
}