package com.example.android.parliament.screens.partyList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.android.parliament.R
import com.example.android.parliament.databinding.FragmentPartyListBinding

class PartyListFragment : Fragment() {
    private lateinit var partyListViewModel: PartyListViewModel
    private lateinit var partyListVmFactory: PartyListVmFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        val binding = DataBindingUtil.inflate<FragmentPartyListBinding>(inflater,
            R.layout.fragment_party_list,container,false)

        partyListVmFactory = PartyListVmFactory(requireContext())
        partyListViewModel = ViewModelProvider(this, partyListVmFactory)
            .get(PartyListViewModel::class.java)

        val partyListAdapter = PartyListAdapter(requireContext())
        binding.partyListRv.adapter = partyListAdapter

        partyListViewModel.allParties.observe(viewLifecycleOwner, {
            partyListAdapter.partyList = it
        })

        return binding.root
    }

}