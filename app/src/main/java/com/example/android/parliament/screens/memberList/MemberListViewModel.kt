package com.example.android.parliament.screens.memberList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.parliament.data.AppDatabase
import com.example.android.parliament.data.AppRepository
import com.example.android.parliament.data.ParliamentMember

//Giang Nguyen - 25.09.2021

class MemberListViewModel(partyName: String) : ViewModel() {
    private val repository: AppRepository = AppRepository(AppDatabase.getDatabase().appDao())

    //member list of a selected party
    private var _memberList: LiveData<List<ParliamentMember>> =
        repository.getMemberList(partyName)

    //navigation includes the personNumber to pass data to next fragment, Member Details Fragment
    private val _navigation = MutableLiveData<Int>()

    val memberList: LiveData<List<ParliamentMember>>
        get() = _memberList

    val navigation: LiveData<Int>
        get() = _navigation

    //a flag for navigation
    fun navigateToMemberDetails(personNumber: Int) {
        _navigation.value = personNumber
    }

    //called after navigation
    fun doneNavigating() {
        _navigation.value = null
    }
}