package com.example.android.parliament.screens.partyList

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.parliament.data.AppDatabase
import com.example.android.parliament.data.AppRepository

class PartyListViewModel(context: Context) : ViewModel() {
    private val repository: AppRepository
    var allParties: LiveData<List<String>>

    init {
        val appDao = AppDatabase.getDatabase(context).appDao()
        repository = AppRepository(appDao)
        allParties = repository.getAllParties()
    }
}