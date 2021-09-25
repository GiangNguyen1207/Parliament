package com.example.android.parliament.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.parliament.data.AppDatabase
import com.example.android.parliament.data.ParliamentMemberRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class AppViewModel(context: Context) : ViewModel() {
    private val repository: ParliamentMemberRepository
    private val response = MutableLiveData<String>()

    init {
        val memberDao = AppDatabase.getDatabase(context).parliamentMemberDao()
        repository = ParliamentMemberRepository(memberDao)
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