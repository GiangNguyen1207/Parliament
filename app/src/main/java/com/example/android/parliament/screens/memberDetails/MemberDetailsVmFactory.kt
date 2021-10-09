package com.example.android.parliament.screens.memberDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//Giang Nguyen - 6.10.2021

class MemberDetailsVmFactory(private val personNumber: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MemberDetailsViewModel(personNumber) as T
    }
}