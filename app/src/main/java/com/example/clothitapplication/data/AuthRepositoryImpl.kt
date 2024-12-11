package com.example.clothitapplication.data

import androidx.compose.runtime.collectAsState
import com.example.clothitapplication.data.repository.remote.ClothitApiService
import com.example.clothitapplication.data.repository.remote.req.UserLoginReq
import com.example.clothitapplication.data.repository.remote.req.UserLogoutReq
import com.example.clothitapplication.data.repository.remote.req.UserRegisterReq
import com.example.clothitapplication.domain.repository.authRepository.AuthRepository
import com.example.clothitapplication.utils.DataStoreManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: ClothitApiService,
    private val dataStoreManager: DataStoreManager
) : AuthRepository {
    override suspend fun login(email: String, password: String): Result<Unit> {
        val response = api.login(UserLoginReq(email, password))
        return try {
            if (response.isSuccessful) {
                response.body()?.let {
                    dataStoreManager.saveAuthToken(it.token)
                    Result.success(Unit)
                } ?: Result.failure(Throwable("Couldn't parse response"))

            } else {
                Result.failure(Throwable("Couldn't find a user with that email and password"))
            }
        } catch (e: Exception) {
            Result.failure(e.message?.let { Throwable(it) } ?: Throwable("Unknown error"))
        }
    }

    override suspend fun register(email: String, password: String, userName: String): Result<Unit> {
        val response = api.register(UserRegisterReq(email, password, userName))
        return try {
            if (response.isSuccessful) {
                response.body()?.let {
                    dataStoreManager.saveAuthToken(it.token)
                    Result.success(Unit)
                } ?: Result.failure(Throwable("Couldn't parse response"))

            } else {
                Result.failure(Throwable("Couldn't register user try to use another email or username"))
            }
        } catch (e: Exception) {
            Result.failure(e.message?.let { Throwable(it) } ?: Throwable("Unknown error"))
        }
    }

    override suspend fun logout(): Result<Unit> {
        val token = runBlocking { dataStoreManager.authToken.first() }
        val response = api.logout(token ?: "")
        return try {
            if (response.isSuccessful) {
                dataStoreManager.clearAuthToken()
                Result.success(Unit)
            } else {
                Result.failure(Throwable("Couldn't logout"))
            }
        } catch (e: Exception) {
            Result.failure(e.message?.let { Throwable(it) } ?: Throwable("Unknown error"))
        }
    }
}

