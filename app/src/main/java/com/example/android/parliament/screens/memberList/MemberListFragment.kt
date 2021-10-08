package com.example.android.parliament.screens.memberList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.parliament.R
import com.example.android.parliament.databinding.FragmentMemberListBinding

class MemberListFragment : Fragment() {
    private lateinit var memberListViewModel: MemberListViewModel
    private lateinit var memberListVmFactory: MemberListVmFactory
    private val args: MemberListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentMemberListBinding>(
            inflater,
            R.layout.fragment_member_list, container, false
        )

        val memberListAdapter = MemberListAdapter(MemberListener { personNumber ->
            memberListViewModel.navigateToMemberDetails(personNumber)
        })

        binding.lifecycleOwner = this
        binding.party = args.party
        binding.memberListRv.adapter = memberListAdapter

        memberListVmFactory = MemberListVmFactory(args.party)
        memberListViewModel =
            ViewModelProvider(this, memberListVmFactory).get(MemberListViewModel::class.java)

        memberListViewModel.memberList.observe(viewLifecycleOwner, {
            memberListAdapter.submitList(it)
        })

        memberListViewModel.navigation.observe(viewLifecycleOwner, { personNumber ->
            if (personNumber != null) {
                this.findNavController().navigate(
                    MemberListFragmentDirections.actionMemberListFragmentToMemberDetailsFragment(
                        personNumber
                    )
                )
                memberListViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}
