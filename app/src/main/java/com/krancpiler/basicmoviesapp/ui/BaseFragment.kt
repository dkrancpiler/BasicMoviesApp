package com.krancpiler.basicmoviesapp.ui

import androidx.fragment.app.Fragment
import com.krancpiler.basicmoviesapp.utility.checkNetworkConnectivity
import com.krancpiler.basicmoviesapp.utility.showNoConnectionDialog
import com.krancpiler.basicmoviesapp.utility.showSimpleMessageDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment: Fragment() {

    override fun onResume() {
        if (!checkNetworkConnectivity()) showNoConnectionDialog()
        super.onResume()
    }

    open fun setUpErrorListening(viewModel: BaseViewModel) {
        viewModel.errorMessage.observe(viewLifecycleOwner, {
            if (it != null) {
                showSimpleMessageDialog(it)
            }
        })
    }
}