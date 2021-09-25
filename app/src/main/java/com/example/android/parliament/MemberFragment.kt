package com.example.android.parliament

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.parliament.databinding.FragmentMemberBinding

class MemberFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentMemberBinding>(
            inflater,
            R.layout.fragment_member, container, false
        )

        return binding.root
    }
}