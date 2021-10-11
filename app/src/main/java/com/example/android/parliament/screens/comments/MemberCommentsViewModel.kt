package com.example.android.parliament.screens.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.android.parliament.data.AppDatabase
import com.example.android.parliament.data.AppRepository
import com.example.android.parliament.data.Comment

//Giang Nguyen - 2.10.2021

class MemberCommentsViewModel(private val personNumber: Int) : ViewModel() {
    private val repository = AppRepository(AppDatabase.getDatabase().appDao())

    //All comments of the user for a MP taken from DB
    private var _allComments: LiveData<List<Comment>> = repository.getMemberComments(personNumber)

    val allComments: LiveData<List<Comment>>
        get() = _allComments
}