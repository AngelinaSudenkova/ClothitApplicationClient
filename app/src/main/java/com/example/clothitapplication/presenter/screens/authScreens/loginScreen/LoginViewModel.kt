package com.example.clothitapplication.presenter.screens.authScreens.loginScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothitapplication.domain.model.ResultUi
import com.example.clothitapplication.domain.usecase.authUC.LoginUserUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUC: LoginUserUC
): ViewModel(){

    private val _loginState = MutableStateFlow<ResultUi<Unit>>(ResultUi.Failure(Throwable("")))
    val loginState: StateFlow<ResultUi<Unit>> = _loginState

    fun login(email: String, password: String){
        viewModelScope.launch {
            _loginState.value = ResultUi.Loading
            val result = loginUserUC(email, password)
            _loginState.value = result.fold(
                onSuccess = { ResultUi.Success(Unit) },
                onFailure = { ResultUi.Failure(it) }
            )
        }
    }

}