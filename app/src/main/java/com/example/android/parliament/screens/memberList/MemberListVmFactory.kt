package com.example.android.parliament.screens.memberList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//Giang Nguyen - 8.10.2021

class MemberListVmFactory(private val partyName: String): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MemberListViewModel(partyName) as T
    }
}