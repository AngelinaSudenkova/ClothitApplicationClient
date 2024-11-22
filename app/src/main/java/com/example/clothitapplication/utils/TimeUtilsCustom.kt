package com.example.clothitapplication.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TimeUtilsCustom {
    fun getCurrentTime(): String {
        val currentTimeMillis = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date(currentTimeMillis))
    }
}