package com.example.clothitapplication.utils

object PasswordUtils {
    fun checkPassword(password: String): Boolean {
        val regex = "^(?=(?:.*[0-9]){1})(?=(?:.*[a-z]){1})(?=(?:.*[A-Z]){1})(?=(?:.*[@#\$%^&+=]){1})(?=\\S+\$).{8}\$".toRegex()
        return regex.matches(password)
    }

    fun checkRepeatedPassword(password: String, repeatedPassword: String): Boolean {
        return password == repeatedPassword
    }
}