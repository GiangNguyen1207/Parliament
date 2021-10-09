package com.example.android.parliament.screens.partyList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.parliament.data.AppDatabase
import com.example.android.parliament.data.AppRepository
import com.example.android.parliament.data.Party

//Giang Nguyen - 25.09.2021

class PartyListViewModel : ViewModel() {
    private val repository: AppRepository = AppRepository(AppDatabase.getDatabase().appDao())

    //a flag for navigation, holding the value of Party Name as a String
    private var _navigation = MutableLiveData<String>()

    //a list of parties collected from db
    private var _allParties: LiveData<List<Party>> = repository.getAllParties()

    val allParties: LiveData<List<Party>>
        get() = _allParties

    val navigation: LiveData<String>
        get() = _navigation

    /* when user clicks a party, this function will be triggered,
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