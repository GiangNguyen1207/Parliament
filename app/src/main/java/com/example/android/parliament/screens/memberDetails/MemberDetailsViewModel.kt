package com.example.android.parliament.screens.memberDetails

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.android.parliament.data.AppDatabase
import com.example.android.parliament.data.AppRepository
import com.example.android.parliament.data.ParliamentMember

class MemberDetailsViewModel : ViewModel() {
    private val repository: AppRepository
    private lateinit var _memberDetails: LiveData<ParliamentMember>

    val memberDetails: LiveData<ParliamentMember>
        get() = _memberDetails

    init {
        val appDao = AppDatabase.getDatabase().appDao()
        repository = AppRepository(appDao)
    }

    fun getMemberDetails(personNumber: Int) {
        _memberDetails = repository.getMemberDetails(personNumber)
    }
}