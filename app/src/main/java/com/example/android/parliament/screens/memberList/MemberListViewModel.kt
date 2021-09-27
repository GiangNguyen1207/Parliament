package com.example.android.parliament.screens.memberList

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.parliament.data.AppDatabase
import com.example.android.parliament.data.AppRepository
import com.example.android.parliament.data.ParliamentMember

class MemberListViewModel(context: Context) : ViewModel() {
    private val repository: AppRepository
    private lateinit var _allMembers: LiveData<List<ParliamentMember>>

    val allMembers: LiveData<List<ParliamentMember>>
        get() = _allMembers

    init {
        val appDao = AppDatabase.getDatabase(context).appDao()
        repository = AppRepository(appDao)

    }

    fun readMemberList(party: String) {
        _allMembers = repository.getMemberList(party)
    }
}