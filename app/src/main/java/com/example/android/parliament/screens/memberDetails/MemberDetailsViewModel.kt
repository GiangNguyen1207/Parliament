package com.example.android.parliament.screens.memberDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.parliament.data.*
import kotlinx.coroutines.launch

//Giang Nguyen - 30.09.2021

class MemberDetailsViewModel(private val personNumber: Int) : ViewModel() {
    private val repository: AppRepository = AppRepository(AppDatabase.getDatabase().appDao())

    //the details of selected members taken from DB
    private var _memberDetails: LiveData<ParliamentMember> =
        repository.getMemberDetails(personNumber)

    //the latest comment taken from DB
    private var _latestComment: LiveData<Comment> = repository.getLatestComment(personNumber)

    //the average rating taken from DB
    private val _averageRate: LiveData<Double> = repository.getAverageRate(personNumber)
    private val _isNavigated = MutableLiveData<Boolean>()

    val memberDetails: LiveData<ParliamentMember>
        get() = _memberDetails

    val latestComment: LiveData<Comment>
        get() = _latestComment

    val averageRate: LiveData<Double>
        get() = _averageRate

    val isNavigated: LiveData<Boolean>
        get() = _isNavigated

    /* function to add rating and comment into DB. If the comment is not empty,
    then add the comment into DB. It helps avoiding null/empty comment. */
    fun addRatingComment(personNumber: Int, rating: Float, comment: String) {
        viewModelScope.launch {
            repository.insertRate(Rating(personNumber = personNumber, rating = rating))

            if (comment.isNotEmpty()) {
                repository.insertComment(
                    Comment(
                        personNumber = personNumber,
                        comment = comment,
                        dateTime = Calendar().getDateAndTime()
                    )
                )
            }
        }
    }

    //a flag to navigate to see all comments
    fun navigateToAllComments() {
        _isNavigated.value = true
    }

    //called after navigation
    fun doneNavigating() {
        _isNavigated.value = false
    }
}