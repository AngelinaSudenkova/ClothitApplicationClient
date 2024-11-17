package com.example.clothitapplication.presenter.screens.authScreens.loginScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.clothitapplication.presenter.components.AuthBackgroundBlur
import com.example.clothitapplication.presenter.components.LoginForm
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@Preview
@Composable
fun LoginScreen() {

    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val hazeState = remember { HazeState() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .haze(hazeState)
        ) {
            AuthBackgroundBlur()
        }
        LoginForm(emailState = emailState, passwordState = passwordState,
            onEmailChange = { emailState.value = it },
            onPasswordChange = { passwordState.value = it },
            onButtonClicked = { /*TODO*/ },
            hazeState = hazeState
        )
    }
}