package com.example.android.parliament.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "member_comment_table")
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo
    val personNumber: Int,

    @ColumnInfo(name = "comment")
    val comment: String
)