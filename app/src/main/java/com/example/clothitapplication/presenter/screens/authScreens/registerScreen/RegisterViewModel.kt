package com.example.clothitapplication.presenter.screens.authScreens.registerScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothitapplication.domain.model.ResultUi
import com.example.clothitapplication.domain.usecase.authUC.RegisterUserUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUC: RegisterUserUC
) : ViewModel() {

    private val _registerState = MutableStateFlow<ResultUi<Unit>>(ResultUi.Failure(Throwable("")))
    val registerState: StateFlow<ResultUi<Unit>> = _registerState

     fun register(email: String, password: String, userName: String) {
        viewModelScope.launch {
            _registerState.value = ResultUi.Loading
            val response = registerUserUC(email, password, userName)
            _registerState.value = response.fold(
                onSuccess = { ResultUi.Success(Unit) },
                onFailure = { ResultUi.Failure(it) }
            )
        }
    }
}