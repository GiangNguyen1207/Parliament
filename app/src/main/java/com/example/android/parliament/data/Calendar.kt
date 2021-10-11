package com.example.android.parliament.data

import java.util.Calendar

//Giang Nguyen - 2.10.2021

//a class to return date, month, year and time in String to insert into DB
class Calendar {
    private val calendar = Calendar.getInstance()

    private fun getMinute(): String {
        val minute = calendar.get(Calendar.MINUTE)
        return if (minute < 10) "0$minute" else "$minute"
    }

    fun getDateAndTime(): String {
        val day = calendar.get(Calendar.DATE)
        val month = calendar.get(Calendar.MONTH) + 1
        val year = calendar.get(Calendar.YEAR)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        return "$day/$month/$year, $hour:${getMinute()}"
    }
}