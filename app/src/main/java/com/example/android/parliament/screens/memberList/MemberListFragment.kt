package com.example.android.parliament.screens.memberList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
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

        val binding = DataBindingUtil.inflate<FragmentMemberListBinding>(inflater,
            R.layout.fragment_member_list,container,false)

        val party = args.party
        val memberListAdapter = MemberListAdapter()

        binding.lifecycleOwner = this
        binding.memberListRv.adapter = memberListAdapter

        memberListVmFactory = MemberListVmFactory(requireContext())
        memberListViewModel = ViewModelProvider(this, memberListVmFactory)
            .get(MemberListViewModel::class.java)

        memberListViewModel.getPartyFinName(party)
        memberListViewModel.readMemberList(party)

        memberListViewModel.partyFinName.observe(viewLifecycleOwner, {
            binding.title.text = getString(R.string.member_list, it)
        })

        memberListViewModel.memberList.observe(viewLifecycleOwner, {
            memberListAdapter.memberList = it
        })

        return binding.root
    }

}
