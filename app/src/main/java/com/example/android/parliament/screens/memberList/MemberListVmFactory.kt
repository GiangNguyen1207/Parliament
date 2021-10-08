package com.example.android.parliament.screens.memberList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MemberListVmFactory(private val partyName: String): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MemberListViewModel(partyName) as T
    }
}