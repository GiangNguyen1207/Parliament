package com.example.android.parliament

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.android.parliament.databinding.FragmentMemberListBinding

class MemberListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentMemberListBinding>(inflater,
            R.layout.fragment_member_list,container,false)

        binding.singleMember.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_memberListFragment_to_memberFragment)
        }

        return binding.root
    }

}