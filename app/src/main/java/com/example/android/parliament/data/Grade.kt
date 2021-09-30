package com.example.android.parliament.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "member_grade_table")
data class Grade (
    @PrimaryKey
    val personNumber: Int,

    @ColumnInfo(name = "grade")
    val grade: Int = 0,
)