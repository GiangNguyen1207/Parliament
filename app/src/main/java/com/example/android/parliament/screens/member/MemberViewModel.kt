package com.example.android.parliament.screens.member

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.parliament.data.AppDatabase
import com.example.android.parliament.data.AppRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MemberViewModel(context: Context) : ViewModel() {
    private val repository: AppRepository

    init {
        val appDao = AppDatabase.getDatabase(context).appDao()
        repository = AppRepository(appDao)
    }
}