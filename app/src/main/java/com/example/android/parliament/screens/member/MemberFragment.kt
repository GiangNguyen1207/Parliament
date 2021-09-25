package com.example.android.parliament.screens.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android.parliament.R
import com.example.android.parliament.databinding.FragmentMemberBinding

class MemberFragment : Fragment() {
    private lateinit var memberViewModel: MemberViewModel
    private lateinit var memberViewModelFactory: MemberViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentMemberBinding>(
            inflater,
            R.layout.fragment_member, container, false
        )

        memberViewModelFactory = MemberViewModelFactory(requireContext())
        memberViewModel = ViewModelProvider(this, memberViewModelFactory)
            .get(MemberViewModel::class.java)

        return binding.root
    }
}