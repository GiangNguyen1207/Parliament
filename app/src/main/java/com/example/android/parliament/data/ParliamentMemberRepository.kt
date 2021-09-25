package com.example.android.parliament.data

import androidx.lifecycle.LiveData

class ParliamentMemberRepository(private val memberDao: ParliamentMemberDao) {
    fun getAllMembers(): LiveData<List<ParliamentMember>> = memberDao.readAllMembers()

    suspend fun insertMember(member: ParliamentMember) = memberDao.insertMember(member)
}