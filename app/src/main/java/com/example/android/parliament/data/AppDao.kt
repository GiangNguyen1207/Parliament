package com.example.android.parliament.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMember(member: ParliamentMember)

    @Query("SELECT * FROM parliament_member_table ORDER BY first_name ASC")
    fun readAllMembers(): LiveData<List<ParliamentMember>>

    @Query("SELECT DISTINCT party, party_in_fin as partyInFin, party_in_eng as partyInEng FROM parliament_member_table ORDER BY party ASC")
    fun readAllParties(): LiveData<List<Party>>

    @Query("SELECT party_in_fin as partyInFin FROM parliament_member_table WHERE party = :party")
    fun readPartyFinName(party: String): LiveData<String>

    @Query("SELECT * FROM parliament_member_table WHERE party = :party ORDER BY first_name ASC")
    fun readMemberList(party: String): LiveData<List<ParliamentMember>>

    @Query("SELECT * FROM parliament_member_table WHERE personNumber = :personNumber")
    fun getMemberDetails(personNumber: Int): LiveData<ParliamentMember>

    @Insert
    suspend fun insertRate(rate: Rating)

    @Insert
    suspend fun insertComment(comment: Comment)

    @Query("SELECT rating from member_rating_table WHERE personNumber = :personNumber")
    fun getMemberRate(personNumber: Int): LiveData<List<Double>>

    @Query("SELECT * from member_comment_table WHERE personNumber = :personNumber")
    fun getMemberComments(personNumber: Int): LiveData<List<Comment>>

    @Query("SELECT picture from parliament_member_table WHERE personNumber = :personNumber")
    fun getMemberPicturePath(personNumber: Int): LiveData<String>
}