package com.example.clothitapplication.domain.model

data class DataOrException<T, L, E : Exception?>(
    var data: T? = null,
    var loading: L? = null,
    var e: E? = null
)