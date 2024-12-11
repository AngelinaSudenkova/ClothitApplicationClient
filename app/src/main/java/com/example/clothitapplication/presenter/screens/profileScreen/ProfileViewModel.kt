package com.example.clothitapplication.presenter.screens.profileScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothitapplication.domain.usecase.authUC.LogoutUserUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val logoutUserUC: LogoutUserUC
) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            logoutUserUC()
        }
    }
}