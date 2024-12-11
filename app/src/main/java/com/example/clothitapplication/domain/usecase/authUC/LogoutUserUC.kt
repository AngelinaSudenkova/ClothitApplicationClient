package com.example.clothitapplication.domain.usecase.authUC

import com.example.clothitapplication.domain.repository.authRepository.AuthRepository
import javax.inject.Inject

class LogoutUserUC @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() = authRepository.logout()
}
