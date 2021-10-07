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

    private var _rates: LiveData<List<Double>> = repository.getMemberRate(personNumber)
    private var _latestComment: LiveData<Comment> = repository.getLatestComment(personNumber)

    private val _isClicked = MutableLiveData<Boolean>()
    private val _averageRate = MutableLiveData<Double>()
    private val _isNavigated = MutableLiveData<Boolean>()

    val memberDetails: LiveData<ParliamentMember>
        get() = _memberDetails

    val rates: LiveData<List<Double>>
        get() = _rates

    val latestComment: LiveData<Comment>
        get() = _latestComment

    val isClicked: LiveData<Boolean>
        get() = _isClicked

    val averageRate: LiveData<Double>
        get() = _averageRate

    val isNavigated: LiveData<Boolean>
        get() = _isNavigated

    fun onButtonClick() {
        _isClicked.value = true
    }

    fun doneClick() {
        _isClicked.value = false
    }

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


    fun calculateAverageRate(rates: List<Double>) {
        if (rates.isNotEmpty()) {
            _averageRate.value = rates.average()
        } else _averageRate.value = 0.0
    }

    fun navigateToAllComments() {
        _isNavigated.value = true
    }

    fun doneNavigating() {
        _isNavigated.value = false
    }
}