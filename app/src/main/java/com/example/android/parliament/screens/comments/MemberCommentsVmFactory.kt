package com.example.android.parliament.screens.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//Giang Nguyen - 11.10.2021

class MemberCommentsVmFactory(private val personNumber: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MemberCommentsViewModel(personNumber) as T
    }
}
