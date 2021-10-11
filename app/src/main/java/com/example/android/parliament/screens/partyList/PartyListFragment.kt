package com.example.android.parliament.screens.partyList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.parliament.R
import com.example.android.parliament.databinding.FragmentPartyListBinding

//Giang Nguyen - 25.09.2021

class PartyListFragment : Fragment() {
    private lateinit var partyListViewModel: PartyListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentPartyListBinding>(
            inflater,
            R.layout.fragment_party_list, container, false
        )

        //initialize the adapter for the list of party
        val partyListAdapter = PartyListAdapter(PartyListener { party ->
            partyListViewModel.navigateToMemberList(party)
        })

        binding.lifecycleOwner = this
        binding.partyListRv.layoutManager = GridLayoutManager(activity, 2)
        binding.partyListRv.adapter = partyListAdapter

        //initialize the view model for the fragment
        partyListViewModel = ViewModelProvider(this)
            .get(PartyListViewModel::class.java)

        /*observe the list of party, if there is a new list,
        then submit it to the adapter to update UI */
        partyListViewModel.allParties.observe(viewLifecycleOwner, {
            partyListAdapter.submitList(it)
        })

        /*observe the users interaction to each party on the screen.
        If they click a specific party -> set the value of navigation with a party name ->
        check if the party is not null -> navigating user to new fragment -> set the value to null */
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