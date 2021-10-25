package com.istgerade.dialog

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.istgerade.R
import com.istgerade.databinding.DialogFragmentBindingImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogFragment : DialogFragment() {

    private val viewModel by viewModels<DialogViewModel>()
    private val args by navArgs<DialogFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DialogFragmentBindingImpl.inflate(inflater)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        setGravity()
        viewModel.translateAd(args.ad)
        binding.exitButton.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

    private fun setGravity(){
        val window: Window? = dialog!!.window
        window?.setGravity(Gravity.CENTER)
    }
}