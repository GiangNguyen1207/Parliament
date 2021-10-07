package com.example.android.parliament.data

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.android.parliament.network.MembersApi

class AppRepository(private val appDao: AppDao) {
    suspend fun fetchAllMembers(): List<ParliamentMember> = MembersApi.retrofitService.fetchAllMembers()

    fun getAllMembers(): LiveData<List<ParliamentMember>> = appDao.readAllMembers()

    suspend fun insertMember(member: ParliamentMember) = appDao.insertMember(member)

    fun getAllParties(): LiveData<List<Party>> = appDao.readAllParties()

    fun getPartyFinName(party: String): LiveData<String> = appDao.readPartyFinName(party)

    fun getMemberList(party: String): LiveData<List<ParliamentMember>> = appDao.readMemberList(party)

    fun getMemberDetails(personNumber: Int): LiveData<ParliamentMember> = appDao.getMemberDetails(personNumber)

    suspend fun insertRate(rate: Rating) = appDao.insertRate(rate)

    suspend fun insertComment(comment: Comment) = appDao.insertComment(comment)

    fun getMemberRate(personNumber: Int): LiveData<List<Double>> = appDao.getMemberRate(personNumber)

    fun getMemberComments(personNumber: Int): LiveData<List<Comment>> = appDao.getMemberComments(personNumber)

    fun getLatestComment(personNumber: Int): LiveData<Comment> = appDao.getLatestComment(personNumber)
}