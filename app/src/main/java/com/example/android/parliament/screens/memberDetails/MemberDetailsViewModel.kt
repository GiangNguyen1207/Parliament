package com.example.android.parliament.screens.memberDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.parliament.data.*
import kotlinx.coroutines.launch

class MemberDetailsViewModel(private val personNumber: Int) : ViewModel() {
    private val repository: AppRepository = AppRepository(AppDatabase.getDatabase().appDao())

    private var _memberDetails: LiveData<ParliamentMember> =
        repository.getMemberDetails(personNumber)

    private var _latestComment: LiveData<Comment> = repository.getLatestComment(personNumber)

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

    fun navigateToAllComments() {
        _isNavigated.value = true
    }

    fun doneNavigating() {
        _isNavigated.value = false
    }
}