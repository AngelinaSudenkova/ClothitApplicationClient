package com.example.clothitapplication.domain.usecase.authUC

import com.example.clothitapplication.domain.repository.authRepository.AuthRepository
import javax.inject.Inject

class RegisterUserUC @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String, userName: String): Result<Unit> {
        return authRepository.register(email, password, userName)
    }
}
