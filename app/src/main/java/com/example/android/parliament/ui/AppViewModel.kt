package com.example.android.parliament.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.android.parliament.data.AppDatabase
import com.example.android.parliament.data.ParliamentMemberRepository

class AppViewModel(context: Context) : ViewModel() {
    private val repository: ParliamentMemberRepository

    init {
        val memberDao = AppDatabase.getDatabase(context).parliamentMemberDao()
        repository = ParliamentMemberRepository(memberDao)
    }
}