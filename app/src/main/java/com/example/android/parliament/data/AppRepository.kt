package com.example.android.parliament.data

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.android.parliament.network.MembersApi

//Giang Nguyen - 25.09.2021

class AppRepository(private val appDao: AppDao) {
    suspend fun fetchAllMembers(): List<ParliamentMember> = MembersApi.retrofitService.fetchAllMembers()

    suspend fun insertMember(member: ParliamentMember) = appDao.insertMember(member)

    fun getAllParties(): LiveData<List<Party>> = appDao.readAllParties()

    fun getMemberList(party: String): LiveData<List<ParliamentMember>> = appDao.readMemberList(party)

    fun getMemberDetails(personNumber: Int): LiveData<ParliamentMember> = appDao.getMemberDetails(personNumber)

    suspend fun insertRate(rate: Rating) = appDao.insertRate(rate)

    suspend fun insertComment(comment: Comment) = appDao.insertComment(comment)

    fun getAverageRate(personNumber: Int): LiveData<Double> = appDao.getAverageRate(personNumber)

    fun getMemberComments(personNumber: Int): LiveData<List<Comment>> = appDao.getMemberComments(personNumber)

    fun getLatestComment(personNumber: Int): LiveData<Comment> = appDao.getLatestComment(personNumber)
}