package com.example.android.parliament.screens.memberList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.parliament.screens.member.MemberViewModel

class MemberListVmFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MemberListViewModel(context) as T
    }
}