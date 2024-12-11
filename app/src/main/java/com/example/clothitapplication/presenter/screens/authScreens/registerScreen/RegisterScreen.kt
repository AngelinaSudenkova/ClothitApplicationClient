package com.example.clothitapplication.presenter.screens.authScreens.registerScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.example.clothitapplication.presenter.components.RegisterForm
import com.example.clothitapplication.presenter.components.TextSign
import com.example.clothitapplication.utils.PasswordUtils
import com.example.clothitapplication.utils.RegisterUtils
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val emailState = rememberSaveable { mutableStateOf("") }
    val usernameState = rememberSaveable { mutableStateOf("") }
    val passwordState = rememberSaveable { mutableStateOf("") }
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    val repeatedPasswordState = rememberSaveable { mutableStateOf("") }
    val repeatedPasswordVisibility = rememberSaveable { mutableStateOf(false) }

    val errorState = remember(
        emailState.value,
        usernameState.value,
        passwordState.value,
        repeatedPasswordState.value
    ) { mutableStateOf("") }
    val registerState = viewModel.registerState.collectAsState()

    val hazeState = remember { HazeState() }

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
                RegisterForm(
                    emailState = emailState,
                    passwordState = passwordState,
                    userName = usernameState,
                    repeatedPasswordState = repeatedPasswordState,
                    onUsernameChange = { usernameState.value = it },
                    onEmailChange = { emailState.value = it },
                    onPasswordChange = { passwordState.value = it },
                    onRepeatedPasswordChange = { repeatedPasswordState.value = it },
                    onButtonClicked = {
                        if (checkInsertedValues(
                                emailState.value,
                                passwordState.value,
                                repeatedPasswordState.value,
                                usernameState.value,
                                errorState
                            )
                        ) {
                            viewModel.register(
                                emailState.value,
                                passwordState.value,
                                usernameState.value
                            )
                        }
                    },
                    hazeState = hazeState,
                    passwordVisibility = passwordVisibility,
                    repeatedPasswordVisibility = repeatedPasswordVisibility
                )
                when (registerState.value) {
                    is ResultUi.Loading -> {
                        LinearProgressIndicator(
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    }

                    is ResultUi.Failure -> {
                        if ((registerState.value as ResultUi.Failure).exception.message?.isNotEmpty() == true) {
                            Text(
                                "Login failed: ${(registerState.value as ResultUi.Failure).exception.message}",
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
                }
                Text(
                    text = errorState.value,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
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
                    TextSign(
                        text = stringResource(R.string.already_have_an_account),
                        clickingText = stringResource(R.string.login_now),
                        onClick = {
                            navController.navigate(NoAuthorizedClothitScreens.LoginScreen.name) {
                                popUpTo(NoAuthorizedClothitScreens.RegisterScreen.name) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

fun checkInsertedValues(
    email: String,
    password: String,
    repeatedPassword: String,
    username: String,
    errorState: MutableState<String>
): Boolean {
//    if (PasswordUtils.checkPassword(password).not()) {
//        errorState.value =
//            "Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 number, 1 special character and be at least 8 characters long"
//        return false
//    }
    if (PasswordUtils.checkRepeatedPassword(password, repeatedPassword).not()) {
        errorState.value = "Passwords do not match"
        return false
    }
    if (RegisterUtils.checkEmail(email).not()) {
        errorState.value = "Invalid email"
        return false
    }
    if (RegisterUtils.checkUserName(username).not()) {
        errorState.value = "Invalid username"
        return false
    }
    return true
}

