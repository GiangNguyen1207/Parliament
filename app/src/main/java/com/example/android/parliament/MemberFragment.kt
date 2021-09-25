package com.example.android.parliament

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android.parliament.databinding.FragmentMemberBinding
import com.example.android.parliament.ui.AppViewModel
import com.example.android.parliament.ui.AppViewModelFactory

class MemberFragment : Fragment() {
    private lateinit var appViewModel: AppViewModel
    private lateinit var appViewModelFactory: AppViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentMemberBinding>(
            inflater,
            R.layout.fragment_member, container, false
        )

        appViewModelFactory = AppViewModelFactory(requireContext())
        appViewModel = ViewModelProvider(this, appViewModelFactory)
            .get(AppViewModel::class.java)

        return binding.root
    }
}