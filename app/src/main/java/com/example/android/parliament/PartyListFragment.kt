package com.example.android.parliament

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.android.parliament.databinding.FragmentPartyListBinding

class PartyListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        val binding = DataBindingUtil.inflate<FragmentPartyListBinding>(inflater,
            R.layout.fragment_party_list,container,false)

        binding.memberList.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_partyListFragment_to_memberListFragment)
        }

        return binding.root
    }

}