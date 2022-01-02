package com.krancpiler.basicmoviesapp.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.krancpiler.basicmoviesapp.R
import com.krancpiler.basicmoviesapp.databinding.DialogSimpleMessageBinding

class NoConnectionDialog(private val listener: NoConnectionConfirmation): DialogFragment() {

    private lateinit var binding: DialogSimpleMessageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogSimpleMessageBinding.inflate(inflater, container, false)
        binding.message.text = resources.getString(R.string.no_connection)
        binding.confirmButton.setOnClickListener {
            dismiss()
            listener.noConnectionDialogClosed()
        }
        this.isCancelable = false
        return binding.root
    }

    interface NoConnectionConfirmation {
        abstract fun noConnectionDialogClosed()
    }
}