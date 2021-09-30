package com.example.android.parliament.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.parliament.network.MembersApi

class AppRepository(private val appDao: AppDao) {
    suspend fun fetchAllMembers(): List<ParliamentMember> = MembersApi.retrofitService.fetchAllMembers()

    fun getAllMembers(): LiveData<List<ParliamentMember>> = appDao.readAllMembers()

    suspend fun insertMember(member: ParliamentMember) = appDao.insertMember(member)

    fun getAllParties(): LiveData<List<Party>> = appDao.readAllParties()

    fun getPartyFinName(party: String): LiveData<String> = appDao.readPartyFinName(party)

    fun getMemberList(party: String): LiveData<List<ParliamentMember>> = appDao.readMemberList(party)
}