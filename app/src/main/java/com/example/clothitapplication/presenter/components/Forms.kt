package com.example.clothitapplication.presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.CodeOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clothitapplication.R
import com.example.clothitapplication.presenter.ui.theme.LocalCustomColorScheme
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild
import dev.chrisbanes.haze.materials.HazeMaterials

@Preview
@Composable
fun RegisterForm(
    modifier: Modifier = Modifier,
    emailState: MutableState<String> = mutableStateOf(""),
    onEmailChange: (String) -> Unit = {},
    userName: MutableState<String> = mutableStateOf(""),
    onUsernameChange: (String) -> Unit = {},
    passwordState: MutableState<String> = mutableStateOf(""),
    passwordVisibility: MutableState<Boolean> = mutableStateOf(true),
    onPasswordChange: (String) -> Unit = {},
    repeatedPasswordState: MutableState<String> = mutableStateOf(""),
    onRepeatedPasswordChange: (String) -> Unit = {},
    repeatedPasswordVisibility: MutableState<Boolean> = mutableStateOf(true),
    onButtonClicked: () -> Unit = {},
    hazeState: HazeState = HazeState()
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.register_ic),
            contentDescription = stringResource(R.string.register_logo),
            tint = Color.White
        )
        Box(
            modifier = Modifier
                .height(445.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(Color.Transparent)
                .hazeChild(state = hazeState, style = HazeMaterials.thin())
                .border(
                    width = Dp.Hairline,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = .8f),
                            Color.White.copy(alpha = .2f),
                        )
                    ),
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = 16.dp,
                        bottomEnd = 16.dp
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                EmailInput(emailState = emailState, onEmailChange = onEmailChange)
                EmailInput(
                    emailState = userName, onEmailChange = onUsernameChange, label = stringResource(
                        R.string.username
                    )
                )
                PasswordInput(
                    passwordState = passwordState, onPasswordChange = onPasswordChange,
                    passwordVisibility = passwordVisibility
                )
                PasswordInput(
                    passwordState = repeatedPasswordState,
                    onPasswordChange = onRepeatedPasswordChange,
                    passwordVisibility = repeatedPasswordVisibility
                )
                SubmitButton {
                    onButtonClicked()
                }
            }
        }
    }

}

@Preview
@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    emailState: MutableState<String> = mutableStateOf(""),
    onEmailChange: (String) -> Unit = {},
    passwordState: MutableState<String> = mutableStateOf(""),
    passwordVisibility: MutableState<Boolean> = mutableStateOf(true),
    onPasswordChange: (String) -> Unit = {},
    onButtonClicked: () -> Unit = {},
    hazeState: HazeState = HazeState()
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.login_ic),
            contentDescription = stringResource(R.string.login_logo),
            tint = Color.White
        )
        Box(
            modifier = Modifier
                .height(345.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(Color.Transparent)
                .hazeChild(state = hazeState, style = HazeMaterials.thin())
                .border(
                    width = Dp.Hairline,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = .8f),
                            Color.White.copy(alpha = .2f),
                        )
                    ),
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = 16.dp,
                        bottomEnd = 16.dp
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                EmailInput(emailState = emailState, onEmailChange = onEmailChange)
                PasswordInput(
                    passwordState = passwordState, onPasswordChange = onPasswordChange,
                    passwordVisibility = passwordVisibility
                )
                SubmitButton {
                    onButtonClicked()
                }
            }
        }
    }
}

@Composable
fun SubmitButton(onButtonClicked: () -> Unit) {
    Button(
        onClick = { onButtonClicked() },
        modifier = Modifier.padding(8.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black
        )
    ) {
        Text(
            text = stringResource(R.string.submit),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
fun TextSign(
    modifier: Modifier = Modifier,
    text: String = "Haven't registered yet?",
    clickingText: String = "Sign up",
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp
        )
        Text(
            text = clickingText,
            modifier = modifier.clickable(
                onClick = onClick
            ),
            color = Color.White,
            fontSize = 16.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )
    }
}


@Preview
@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    emailState: MutableState<String> = mutableStateOf(""),
    onEmailChange: (String) -> Unit = {},
    label: String = stringResource(R.string.email),
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        modifier = modifier,
        value = emailState.value,
        onValueChange = onEmailChange,
        label = { Text(text = label) },
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 18.sp, color = LocalCustomColorScheme.current.onInputField
        ),
        shape = MaterialTheme.shapes.small,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = imeAction),
        keyboardActions = onAction,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White, disabledContainerColor = Color.White
        )
    )
}

@Preview
@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    passwordState: MutableState<String> = mutableStateOf(""),
    onPasswordChange: (String) -> Unit = {},
    label: String = stringResource(R.string.password),
    passwordVisibility: MutableState<Boolean> = mutableStateOf(true),
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    val visualTransformation = if (passwordVisibility.value) VisualTransformation.None
    else PasswordVisualTransformation()

    OutlinedTextField(
        modifier = modifier,
        value = passwordState.value,
        onValueChange = onPasswordChange,
        label = { Text(text = label) },
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 18.sp, color = LocalCustomColorScheme.current.onInputField
        ),
        shape = MaterialTheme.shapes.small,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password, imeAction = imeAction
        ),
        visualTransformation = visualTransformation,
        trailingIcon = { PasswordVisibility(passwordVisibility = passwordVisibility) },
        keyboardActions = onAction,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White, disabledContainerColor = Color.White
        )
    )
}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(modifier = Modifier.size(24.dp), onClick = { passwordVisibility.value = !visible }) {
        Icon(
            imageVector = if (visible) Icons.Filled.Code else Icons.Filled.CodeOff,
            contentDescription = if (visible) stringResource(R.string.hide_password) else stringResource(
                R.string.show_password
            )
        )
    }
}
