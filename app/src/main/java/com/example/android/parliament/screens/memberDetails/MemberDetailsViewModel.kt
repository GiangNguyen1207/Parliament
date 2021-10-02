package com.example.android.parliament.screens.memberDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.parliament.data.*
import kotlinx.coroutines.launch

class MemberDetailsViewModel : ViewModel() {
    private val repository: AppRepository
    private lateinit var _memberDetails: LiveData<ParliamentMember>
    private val _isClicked = MutableLiveData<Boolean>()
    private lateinit var _rates: LiveData<List<Double>>
    private val _averageRate = MutableLiveData<Double>()
    private lateinit var _allComments: LiveData<List<String>>
    private val _isNavigated = MutableLiveData<Boolean>()

    val memberDetails: LiveData<ParliamentMember>
        get() = _memberDetails

    val isClicked: LiveData<Boolean>
        get() = _isClicked

    val rates: LiveData<List<Double>>
        get() = _rates

    val averageRate: LiveData<Double>
        get() = _averageRate

    val allComments: LiveData<List<String>>
        get() = _allComments

    val isNavigatedd: LiveData<Boolean>
        get() = _isNavigated

    init {
        val appDao = AppDatabase.getDatabase().appDao()
        repository = AppRepository(appDao)
    }

    fun getMemberDetails(personNumber: Int) {
        _memberDetails = repository.getMemberDetails(personNumber)
    }

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

    fun getMemberRate(personNumber: Int) {
        _rates = repository.getMemberRate(personNumber)
    }

    /*fun getMemberComments(personNumber: Int) {
        _allComments = repository.getMemberComments(personNumber)
    }*/

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