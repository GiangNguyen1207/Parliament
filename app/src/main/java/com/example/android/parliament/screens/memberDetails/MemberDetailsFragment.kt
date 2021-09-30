package com.example.android.parliament.screens.memberDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android.parliament.R
import com.example.android.parliament.databinding.FragmentMemberDetailsBinding

class MemberDetailsFragment : Fragment() {
    private lateinit var memberDetailsViewModel: MemberDetailsViewModel
    private lateinit var memberDetailsVmlFactory: MemberDetailsVmlFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentMemberDetailsBinding>(
            inflater,
            R.layout.fragment_member_details, container, false
        )

        memberDetailsVmlFactory = MemberDetailsVmlFactory(requireContext())
        memberDetailsViewModel = ViewModelProvider(this, memberDetailsVmlFactory)
            .get(MemberDetailsViewModel::class.java)

        return binding.root
    }
}