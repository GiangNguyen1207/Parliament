package com.example.android.parliament.screens.partyList

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
        fetchParliamentMembers()
    }

    /*fetch parliament members from the Internet, then change some properties
    and insert directly to the database */
    private fun fetchParliamentMembers() {
        viewModelScope.launch {
            try {
                val allMembers = repository.fetchAllMembers()
                for (member in allMembers) {
                    val mem = ParliamentMember(
                        member.personNumber, member.seatNumber, member.last,
                        member.first, member.party, MyApp.appContext.getString(getFinName(member.party)),
                        MyApp.appContext.getString(getEngName(member.party)), member.minister,
                        member.bornYear, member.constituency
                    )
                    repository.insertMember(mem)
                    response.value = "Successfully"
                }

            } catch (error: Exception) {
                response.value = "Failure + ${error.message}"
            }
        }
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

    //get the Finnish name of the party to put in the database table column
    private fun getFinName(partyName: String): Int {
        return when (partyName) {
            "ps" -> R.string.ps
            "sd" -> R.string.sd
            "vihr" -> R.string.vihr
            "kok" -> R.string.kok
            "r" -> R.string.r
            "kd" -> R.string.kd
            "vas" -> R.string.vas
            "liik" -> R.string.liik
            else -> R.string.kesk
        }
    }

    //get the English name of the party to put in the database table column
    private fun getEngName(partyName: String): Int {
        return when (partyName) {
            "ps" -> R.string.ps_eng
            "sd" -> R.string.sd_eng
            "vihr" -> R.string.vihr_eng
            "kok" -> R.string.kok_eng
            "r" -> R.string.r_eng
            "kd" -> R.string.kd_eng
            "vas" -> R.string.vas_eng
            "liik" -> R.string.liik_eng
            else -> R.string.kesk_eng
        }
    }
}