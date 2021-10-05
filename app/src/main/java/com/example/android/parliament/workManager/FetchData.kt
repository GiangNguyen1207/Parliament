package com.example.android.parliament.workManager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.android.parliament.MyApp
import com.example.android.parliament.R
import com.example.android.parliament.data.AppDatabase
import com.example.android.parliament.data.AppRepository
import com.example.android.parliament.data.ParliamentMember
import retrofit2.HttpException

class FetchDataWorker(context: Context, params: WorkerParameters) : CoroutineWorker(
    MyApp.appContext,
    params
) {

    companion object {
        const val WORK_NAME = "FetchData"
    }

    override suspend fun doWork(): Result {
        val appDao = AppDatabase.getDatabase().appDao()
        val repository = AppRepository(appDao)

        /*fetch parliament members from the Internet, then change some properties
        and insert directly to the database */
        try {
            val allMembers = repository.fetchAllMembers()
            for (member in allMembers) {
                val mem = ParliamentMember(
                    member.personNumber,
                    member.seatNumber,
                    member.last,
                    member.first,
                    member.party,
                    MyApp.appContext.getString(getFinName(member.party)),
                    MyApp.appContext.getString(getEngName(member.party)),
                    member.minister,
                    member.bornYear,
                    member.constituency,
                    member.picture
                )
                repository.insertMember(mem)
            }
        } catch (exception: HttpException) {
            return Result.retry()
        }

        return Result.success()
    }
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