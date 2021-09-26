package com.example.android.parliament.screens.partyList

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.android.parliament.R
import com.example.android.parliament.data.AppDatabase
import com.example.android.parliament.data.AppRepository
import com.example.android.parliament.data.ParliamentMember
import com.example.android.parliament.data.Party
import kotlinx.coroutines.launch

class PartyListViewModel(context: Context) : ViewModel() {
    private val repository: AppRepository
    private val response = MutableLiveData<String>()
    private var _navigation = MutableLiveData<String>()
    var allParties: LiveData<List<Party>>

    val navigation: LiveData<String>
        get() = _navigation

    init {
        val appDao = AppDatabase.getDatabase(context).appDao()
        repository = AppRepository(appDao)
        allParties = repository.getAllParties()
        fetchParliamentMembers(context)
    }

    private fun fetchParliamentMembers(context: Context) {
        viewModelScope.launch {
            try {
                val allMembers = repository.fetchAllMembers()
                for (member in allMembers) {
                    val mem = ParliamentMember(
                        member.personNumber, member.seatNumber, member.last,
                        member.first, member.party, context.getString(displayFinName(member.party)),
                        context.getString(displayEngName(member.party)), member.minister,
                        member.bornYear, member.constituency
                    )
                    repository.insertMember(mem)
                }

            } catch (error: Exception) {
                response.value = "Failure + ${error.message}"
            }
        }
    }

    fun navigateToMemberList(party: String) {
        _navigation.value = party
    }

    fun doneNavigating() {
        _navigation.value = null
    }

    private fun displayFinName(partyName: String): Int {
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

    private fun displayEngName(partyName: String): Int {
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