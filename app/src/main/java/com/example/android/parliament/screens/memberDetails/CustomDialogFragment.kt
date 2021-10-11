package com.example.android.parliament.screens.memberDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.*
import com.example.android.parliament.R
import com.example.android.parliament.databinding.FragmentCustomDialogBinding

//Giang Nguyen - 8.10.2021

//the dialog fragment created when user click the button submit and the rate is 0
class CustomDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentCustomDialogBinding>(
            inflater,
            R.layout.fragment_custom_dialog, container, false
        )

        //when user clicks Negative button(No/Back) -> dismiss the dialog
        binding.btnNegative.setOnClickListener {
            dismiss()
        }

        //when user clicks Positive button(Yes/Ok/Submit) -> send data back to previous fragment
        binding.btnPositive.setOnClickListener {
            setFragmentResult("onBtnPositiveClick", bundleOf("isSubmitted" to true))
            dismiss()
        }

        return binding.root
    }

}