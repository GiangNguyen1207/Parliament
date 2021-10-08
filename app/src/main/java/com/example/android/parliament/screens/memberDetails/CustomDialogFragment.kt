package com.example.android.parliament.screens.memberDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.*
import com.example.android.parliament.R
import com.example.android.parliament.databinding.FragmentCustomDialogBinding

class CustomDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentCustomDialogBinding>(
            inflater,
            R.layout.fragment_custom_dialog, container, false
        )

        binding.btnNegative.setOnClickListener {
            dismiss()
        }

        binding.btnPositive.setOnClickListener {
            setFragmentResult("onBtnPositiveClick", bundleOf("isSubmitted" to true))
            dismiss()
        }

        return binding.root
    }

}