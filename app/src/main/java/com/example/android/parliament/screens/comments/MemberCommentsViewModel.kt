package com.example.android.parliament.screens.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.android.parliament.data.AppDatabase
import com.example.android.parliament.data.AppRepository
import com.example.android.parliament.data.Comment

//Giang Nguyen - 2.10.2021

class MemberCommentsViewModel: ViewModel() {
    private val repository: AppRepository

    //All comments of the user for a MP taken from DB
    private lateinit var _allComments: LiveData<List<Comment>>

    val allComments: LiveData<List<Comment>>
        get() = _allComments

    init {
        val appDao = AppDatabase.getDatabase().appDao()
        repository = AppRepository(appDao)
    }

    fun getAllComments(personNumber: Int) {
        _allComments = repository.getMemberComments(personNumber)
    }
}