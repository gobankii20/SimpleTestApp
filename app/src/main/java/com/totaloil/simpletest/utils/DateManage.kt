package com.totaloil.simpletest.utils

import android.text.format.DateFormat
import java.util.*

class DateManage {

    companion object {
        const val DATE_FORMAT = "dd-MM-yyyy"
    }

    fun timeStampToDateFormat(time: Long): String {
        val cal: Calendar = Calendar.getInstance(Locale.ENGLISH)
        cal.timeInMillis = time * 1000
        return DateFormat.format(DATE_FORMAT, cal).toString()
    }

}