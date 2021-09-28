package com.example.android.parliament.screens.partyList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.parliament.R
import com.example.android.parliament.databinding.FragmentPartyListBinding

class PartyListFragment : Fragment() {
    private lateinit var partyListViewModel: PartyListViewModel
    private lateinit var partyListVmFactory: PartyListVmFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentPartyListBinding>(
            inflater,
            R.layout.fragment_party_list, container, false
        )

        val partyListAdapter = PartyListAdapter(PartyListener { party ->
            partyListViewModel.navigateToMemberList(party)
        })

        binding.lifecycleOwner = this
        binding.partyListRv.adapter = partyListAdapter

        partyListVmFactory = PartyListVmFactory(requireContext())
        partyListViewModel = ViewModelProvider(this, partyListVmFactory)
            .get(PartyListViewModel::class.java)

        partyListViewModel.allParties.observe(viewLifecycleOwner, {
            partyListAdapter.partyList = it
        })

        partyListViewModel.navigation.observe(viewLifecycleOwner, { party ->
            if (party != null) {
                this.findNavController().navigate(
                    PartyListFragmentDirections.actionPartyListFragmentToMemberListFragment(party)
                )
                partyListViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}