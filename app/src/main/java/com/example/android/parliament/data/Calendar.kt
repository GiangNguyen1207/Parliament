package com.example.android.parliament.data

import java.util.Calendar

//Giang Nguyen - 2.10.2021

//a class to return date, month, year and time in String to insert into DB
class Calendar {
    private val months = listOf("January", "February", "March", "April", "May",
        "June", "July", "August", "September", "October", "November", "December")
    private val calendar = Calendar.getInstance()

    fun getDateAndTime(): String {
        val day = calendar.get(Calendar.DATE)
        val month = months[calendar.get(Calendar.MONTH)]
        val year = calendar.get(Calendar.YEAR)
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)

        return "$day $month, $year, $hour:$minute"
    }
}