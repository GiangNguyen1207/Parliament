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
}