package com.example.clothitapplication.domain.usecase.authUC

import com.example.clothitapplication.domain.repository.authRepository.AuthRepository
import javax.inject.Inject

class LoginUserUC @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) = repository.login(email, password)
}