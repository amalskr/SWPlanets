package com.ainext.swplanets.utils.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toDateString(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(Date(this))
}