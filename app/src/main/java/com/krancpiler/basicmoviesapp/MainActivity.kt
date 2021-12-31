package com.krancpiler.basicmoviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
            findNavController( R.id.nav_host_fragment).navigate(R.id.globalActionLogin)
        }
    }

    fun setToolbarVisible() {
        binding.toolbar.visibility = View.VISIBLE
    }

    fun changeToolbarTitle(title: String) {
        binding.toolbarTitle.text = title
    }


}