package com.example.android.parliament.screens.memberList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.parliament.data.AppDatabase
import com.example.android.parliament.data.AppRepository
import com.example.android.parliament.data.ParliamentMember

class MemberListViewModel(partyName: String) : ViewModel() {
    private val repository: AppRepository = AppRepository(AppDatabase.getDatabase().appDao())
    private var _memberList: LiveData<List<ParliamentMember>> =
        repository.getMemberList(partyName)
    private val _navigation = MutableLiveData<Int>()

    val memberList: LiveData<List<ParliamentMember>>
        get() = _memberList

    val navigation: LiveData<Int>
        get() = _navigation

    fun navigateToMemberDetails(personNumber: Int) {
        _navigation.value = personNumber
    }

    fun doneNavigating() {
        _navigation.value = null
    }
}