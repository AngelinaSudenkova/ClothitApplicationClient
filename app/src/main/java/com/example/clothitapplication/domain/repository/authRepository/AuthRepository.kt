package com.example.clothitapplication.domain.repository.authRepository


interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
    suspend fun register(email: String, password: String, userName: String): Result<Unit>
    suspend fun logout(): Result<Unit>
}