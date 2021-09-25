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
    private val response = MutableLiveData<String>()

    init {
        val appDao = AppDatabase.getDatabase(context).appDao()
        repository = AppRepository(appDao)
        fetchParliamentMembers()
    }

    private fun fetchParliamentMembers() {
        viewModelScope.launch {
            try {
                val allMembers = repository.fetchAllMembers()
                for (member in allMembers) {
                    repository.insertMember(member)
                }

            } catch(error: Exception) {
                response.value = "Failure + ${error.message}"
            }
        }
    }
}