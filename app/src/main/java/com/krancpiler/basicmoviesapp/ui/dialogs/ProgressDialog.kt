package com.krancpiler.basicmoviesapp.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.krancpiler.basicmoviesapp.databinding.DialogProgressBinding

class ProgressDialog: DialogFragment() {

 private lateinit var binding: DialogProgressBinding

 override fun onCreateView(
  inflater: LayoutInflater,
  container: ViewGroup?,
  savedInstanceState: Bundle?
 ): View? {
  binding = DialogProgressBinding.inflate(inflater, container,false)
  return binding.root
 }
}