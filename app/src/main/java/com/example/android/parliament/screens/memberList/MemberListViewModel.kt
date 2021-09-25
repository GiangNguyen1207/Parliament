package com.example.android.parliament.screens.memberList

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.android.parliament.data.AppDatabase
import com.example.android.parliament.data.AppRepository

class MemberListViewModel(context: Context) : ViewModel() {
    private val repository: AppRepository

    init {
        val appDao = AppDatabase.getDatabase(context).appDao()
        repository = AppRepository(appDao)
    }
}