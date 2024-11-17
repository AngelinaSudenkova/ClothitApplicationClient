package com.example.clothitapplication.presenter.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color


val LocalCustomColorScheme = compositionLocalOf { LightCustomColorScheme }

private val DarkCustomColorScheme = CustomColorScheme(
    primary = grayBackground,
    background = grayBackground,
    surface = graySurface,
    onPrimary = Color.White,
    onBackground = Color.White,
    onSurface = onGraySurface,
    pinkAccent = pinkAccent,
    yellowAccent = yellowAccent,
    blueAccent = blueAccent,
    purpleAccent = purpleAccent
)

private val LightCustomColorScheme = CustomColorScheme(
    primary = grayBackground,
    background = grayBackground,
    surface = graySurface,
    onPrimary = Color.White,
    onBackground = Color.White,
    onSurface = onGraySurface,
    pinkAccent = pinkAccent,
    yellowAccent = yellowAccent,
    blueAccent = blueAccent,
    purpleAccent = purpleAccent
)

@Composable
fun ClothitApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val customColorScheme = if (darkTheme) DarkCustomColorScheme else LightCustomColorScheme
    val materialColorScheme = customColorScheme.toMaterialColorScheme()

    MaterialTheme(
        colorScheme = materialColorScheme,
        typography = Typography
    ){
        CompositionLocalProvider(LocalCustomColorScheme provides customColorScheme) {
            content()
        }
    }
}

fun CustomColorScheme.toMaterialColorScheme() = lightColorScheme(
    primary = primary,
    background = background,
    surface = surface,
    onPrimary = onPrimary,
    onBackground = onBackground,
    onSurface = onSurface
)

data class CustomColorScheme(
    val primary: Color,
    val background: Color,
    val surface: Color,
    val onPrimary: Color,
    val onBackground: Color,
    val onSurface: Color,
    val pinkAccent: Color,
    val yellowAccent: Color,
    val blueAccent: Color,
    val purpleAccent: Color
)