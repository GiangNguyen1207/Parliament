package com.example.android.parliament.screens.partyList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.parliament.MyApp
import com.example.android.parliament.R
import com.example.android.parliament.data.AppDatabase
import com.example.android.parliament.data.AppRepository
import com.example.android.parliament.data.ParliamentMember
import com.example.android.parliament.data.Party
import kotlinx.coroutines.launch

class PartyListViewModel : ViewModel() {
    private val repository: AppRepository

    //response from fetching data from the Internet
    private val response = MutableLiveData<String>()

    //a flag for navigation
    private var _navigation = MutableLiveData<String>()

    //a list of parties collected from db
    private var _allParties: LiveData<List<Party>>

    val allParties: LiveData<List<Party>>
        get() = _allParties

    val navigation: LiveData<String>
        get() = _navigation

    /*initialize database, list of parties and fetching data from the Internet in the beginning
    when the app is launched */
    init {
        val appDao = AppDatabase.getDatabase().appDao()
        repository = AppRepository(appDao)
        _allParties = repository.getAllParties()
        //fetchParliamentMembers()
    }

    /*when user clicks a party, this function will be triggered,
    and the party name will be saved into navigation value.
    If the value is not null, user will be navigated to Member List of the specific party. */
    fun navigateToMemberList(party: String) {
        _navigation.value = party
    }

    //call right after navigating to the Member List Fragment to avoid infinite loop
    fun doneNavigating() {
        _navigation.value = null
    }
}