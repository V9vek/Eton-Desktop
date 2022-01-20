package com.eton.ui.feature.notes.util

import java.text.SimpleDateFormat
import java.util.*

fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("MMMM dd, hh:mm a")
    return format.format(date)
}

fun convertDateToLong(date: String): Long {
    val df = SimpleDateFormat("yyyy.MM.dd HH:mm")
    return df.parse(date).time
}