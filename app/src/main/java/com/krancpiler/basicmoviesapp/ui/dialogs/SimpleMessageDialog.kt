package com.krancpiler.basicmoviesapp.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.krancpiler.basicmoviesapp.databinding.DialogSimpleMessageBinding
import com.krancpiler.basicmoviesapp.utility.RegularConstants.SIMPLE_MESSAGE_DIALOG_ARGUMENT

class SimpleMessageDialog: DialogFragment() {

    private lateinit var binding: DialogSimpleMessageBinding

    fun newInstance(message: String): SimpleMessageDialog {
        val args = Bundle()
        args.putString(SIMPLE_MESSAGE_DIALOG_ARGUMENT, message)
        val fragment = SimpleMessageDialog()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogSimpleMessageBinding.inflate(inflater, container, false)
        val message = arguments?.getString(SIMPLE_MESSAGE_DIALOG_ARGUMENT)
        if (message != null) binding.message.text = message
        init()
        return binding.root
    }

    private fun init() {
        binding.confirmButton.setOnClickListener { view ->
            this.dismiss()
        }
    }


}