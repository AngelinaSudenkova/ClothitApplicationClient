package com.example.clothitapplication.presenter.screens.authScreens.registerScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clothitapplication.R
import com.example.clothitapplication.navigation.NoAuthorizedClothitScreens
import com.example.clothitapplication.presenter.components.AuthBackgroundBlur
import com.example.clothitapplication.presenter.components.LoginForm
import com.example.clothitapplication.presenter.components.RegisterForm
import com.example.clothitapplication.presenter.components.TextSign
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

@Composable
fun RegisterScreen(
    navController: NavController
) {
    val emailState = rememberSaveable { mutableStateOf("") }
    val usernameState = rememberSaveable { mutableStateOf("") }
    val passwordState = rememberSaveable { mutableStateOf("") }
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    val repeatedPasswordState = rememberSaveable { mutableStateOf("") }
    val repeatedPasswordVisibility = rememberSaveable { mutableStateOf(false) }

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
            RegisterForm(
                emailState = emailState,
                passwordState = passwordState,
                userName = usernameState,
                repeatedPasswordState = repeatedPasswordState,
                onUsernameChange = { usernameState.value = it },
                onEmailChange = { emailState.value = it },
                onPasswordChange = { passwordState.value = it },
                onRepeatedPasswordChange = { repeatedPasswordState.value = it },
                onButtonClicked = { /*TODO*/ },
                hazeState = hazeState,
                passwordVisibility = passwordVisibility,
                repeatedPasswordVisibility = repeatedPasswordVisibility
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
                    onClick = { navController.navigate(NoAuthorizedClothitScreens.LoginScreen.name){
                        popUpTo(NoAuthorizedClothitScreens.RegisterScreen.name) { inclusive = true}
                    } }
                )
            }
        }
    }
}
