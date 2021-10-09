package com.example.android.parliament.screens.comments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.android.parliament.R
import com.example.android.parliament.databinding.FragmentAllCommentsBinding
import com.example.android.parliament.screens.memberDetails.MemberDetailsFragmentArgs

//Giang Nguyen - 2.10.2021

class MemberCommentsFragment : Fragment() {
    private val args: MemberDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentAllCommentsBinding>(
            inflater,
            R.layout.fragment_all_comments, container, false
        )

        val memberCommentsViewModel: MemberCommentsViewModel =
            ViewModelProvider(this).get(MemberCommentsViewModel::class.java)

        val allCommentsAdapter = AllCommentsAdapter()

        binding.lifecycleOwner = this
        binding.commentRv.adapter = allCommentsAdapter

        memberCommentsViewModel.getAllComments(args.personNumber)

        //observe and show the latest comment list
        memberCommentsViewModel.allComments.observe(viewLifecycleOwner, {
            allCommentsAdapter.submitList(it)
        })

        return binding.root
    }
}