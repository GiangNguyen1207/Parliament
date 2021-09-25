package com.example.android.parliament.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.parliament.network.MembersApi
import kotlinx.coroutines.launch
import java.lang.Exception

class ParliamentMemberRepository(private val memberDao: ParliamentMemberDao) {
    suspend fun fetchAllMembers(): List<ParliamentMember> = MembersApi.retrofitService.fetchAllMembers()

    fun getAllMembers(): LiveData<List<ParliamentMember>> = memberDao.readAllMembers()

    suspend fun insertMember(member: ParliamentMember) = memberDao.insertMember(member)
}