package com.example.clothitapplication.presenter.screens.authScreens.loginScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.clothitapplication.R
import com.example.clothitapplication.domain.model.ResultUi
import com.example.clothitapplication.navigation.Graph
import com.example.clothitapplication.navigation.NoAuthorizedClothitScreens
import com.example.clothitapplication.presenter.components.AuthBackgroundBlur
import com.example.clothitapplication.presenter.components.LoginForm
import com.example.clothitapplication.presenter.components.TextSign
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val emailState = rememberSaveable { mutableStateOf("") }
    val passwordState = rememberSaveable { mutableStateOf("") }
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    val hazeState = remember { HazeState() }

    val loginState = viewModel.loginState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .haze(hazeState)
        ) {
            AuthBackgroundBlur()
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LoginForm(
                    emailState = emailState, passwordState = passwordState,
                    onEmailChange = { emailState.value = it },
                    onPasswordChange = { passwordState.value = it },
                    onButtonClicked = {
                        viewModel.login(emailState.value, passwordState.value)
                    },
                    hazeState = hazeState,
                    passwordVisibility = passwordVisibility
                )
                when (loginState.value) {
                    is ResultUi.Failure -> {
                        if ((loginState.value as ResultUi.Failure).exception.message?.isNotEmpty() == true) {
                            Text(
                                "Login failed: ${(loginState.value as ResultUi.Failure).exception.message}",
                                modifier = Modifier.padding(8.dp),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }

                    is ResultUi.Success -> {
                        navController.navigate(Graph.HOME) {
                            popUpTo(Graph.AUTH) {
                                inclusive = true
                            }
                        }
                    }

                    is ResultUi.Loading -> {
                        LinearProgressIndicator(
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 64.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(R.drawable.clothit_logo),
                    contentDescription = stringResource(R.string.clothit_logo),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                TextSign(onClick = {
                    navController.navigate(NoAuthorizedClothitScreens.RegisterScreen.name) {
                        popUpTo(NoAuthorizedClothitScreens.LoginScreen.name) {
                            inclusive = true
                        }
                    }
                })
            }
        }
    }
}
