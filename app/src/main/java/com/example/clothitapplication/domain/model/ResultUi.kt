package com.example.clothitapplication.domain.model

sealed class ResultUi<out T> {
    data class Success<out T>(val data: T): ResultUi<T>()
    data class Failure(val exception: Throwable): ResultUi<Nothing>()
    object Loading: ResultUi<Nothing>()
}