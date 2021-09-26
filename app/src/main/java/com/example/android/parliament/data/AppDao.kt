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

    /*@Query("SELECT * FROM parliament_member_table WHERE party")
    fun readMemberList(): LiveData<List<ParliamentMember>>*/
}