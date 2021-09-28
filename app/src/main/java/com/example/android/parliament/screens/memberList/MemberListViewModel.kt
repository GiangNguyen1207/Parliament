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
    private var _partyName = MutableLiveData<String>()
    private lateinit var _memberList: LiveData<List<ParliamentMember>>

    val memberList: LiveData<List<ParliamentMember>>
        get() = _memberList

    val partyName: LiveData<String>
        get() = _partyName

    init {
        val appDao = AppDatabase.getDatabase(context).appDao()
        repository = AppRepository(appDao)
    }

    fun getPartyFinName(party: String) {
        val partyNameList = repository.getPartyFinName(party)
    }

    fun readMemberList(party: String) {
        _memberList = repository.getMemberList(party)
    }

}