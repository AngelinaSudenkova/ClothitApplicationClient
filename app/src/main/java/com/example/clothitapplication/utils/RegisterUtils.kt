package com.example.clothitapplication.utils

object RegisterUtils {
    fun checkEmail(email: String): Boolean {
        val regex = "^[A-Za-z0-9+_.-]+@(.+)\$".toRegex()
        return regex.matches(email)
    }
    fun checkUserName(userName: String): Boolean {
        val regex = "^[a-zA-Z0-9_]*$".toRegex()
        return regex.matches(userName)
    }
}