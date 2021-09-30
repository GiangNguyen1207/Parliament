package com.example.android.parliament.screens.memberDetails

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.android.parliament.data.AppDatabase
import com.example.android.parliament.data.AppRepository

class MemberDetailsViewModel(context: Context) : ViewModel() {
    private val repository: AppRepository

    init {
        val appDao = AppDatabase.getDatabase().appDao()
        repository = AppRepository(appDao)
    }
}