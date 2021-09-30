package com.example.android.parliament.screens.memberDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.android.parliament.R
import com.example.android.parliament.databinding.FragmentMemberDetailsBinding
import java.util.*

class MemberDetailsFragment : Fragment() {
    private lateinit var memberDetailsViewModel: MemberDetailsViewModel
    private val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    private val args: MemberDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentMemberDetailsBinding>(
            inflater,
            R.layout.fragment_member_details, container, false
        )

        val personNumber = args.personNumber

        binding.lifecycleOwner = this

        memberDetailsViewModel = ViewModelProvider(this)
            .get(MemberDetailsViewModel::class.java)

        memberDetailsViewModel.getMemberDetails(personNumber)

        memberDetailsViewModel.memberDetails.observe(viewLifecycleOwner, { member ->
            binding.member = member
            binding.age.text = getString(R.string.age, currentYear - member.bornYear)
        })

        return binding.root
    }
}