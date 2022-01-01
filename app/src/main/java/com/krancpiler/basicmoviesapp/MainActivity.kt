package com.krancpiler.basicmoviesapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.krancpiler.basicmoviesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val mainViewModel: MainSharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        init()
        setContentView(binding.root)
    }

    private fun init() {
        binding.logoutText.setOnClickListener {
            mainViewModel.deleteUserInfo()
            binding.toolbar.visibility = View.GONE
            findNavController(R.id.nav_host_fragment).navigate(R.id.globalActionLogin)
        }
        binding.searchEditText.setOnEditorActionListener{ view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
//                homeViewModel.searchMovies(view.text.toString())
                val action = MainNavGraphDirections.globalSearchFragment(view.text.toString())
                findNavController(R.id.nav_host_fragment).navigate(action)
                true
            } else false
        }
    }

    fun setToolbarVisible() {
        binding.toolbar.visibility = View.VISIBLE
    }

    fun changeToolbarTitle(title: String) {
        binding.toolbarTitle.text = title
    }

    override fun onDestroy() {
        if (mainViewModel.getUserInfo() != null && !mainViewModel.getUserInfo()?.isAuthorized!!) mainViewModel.deleteUserInfo()
        super.onDestroy()
    }
}