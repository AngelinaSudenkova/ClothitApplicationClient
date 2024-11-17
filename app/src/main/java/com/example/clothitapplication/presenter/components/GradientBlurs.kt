package com.example.clothitapplication.presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clothitapplication.presenter.ui.theme.LocalCustomColorScheme


@Preview
@Composable
fun GradientBlur(
    color: Color =  LocalCustomColorScheme.current.pinkAccent,
    offsetX : Float = 100f,
    offsetY : Float = 0f,
){
    val gradientBrush = Brush.radialGradient(
        colors = listOf(color.copy(alpha = 0.5f), color.copy(alpha = 0.0f)),
        center = Offset(offsetX, offsetY),
        radius = 1200f
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush)
            .blur(50.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
    )
}

@Preview
@Composable
fun SplashBackgroundBlur(
    colorCenterLeft : Color = LocalCustomColorScheme.current.pinkAccent,
    colorTopRight: Color = LocalCustomColorScheme.current.yellowAccent,
    colorBottomRight: Color = LocalCustomColorScheme.current.purpleAccent
){
    val screenWidth = Size.width().toFloat()
    val screenHeight = Size.height().toFloat()

    GradientBlur(color = colorCenterLeft, offsetX = 0f, offsetY = screenHeight)
    GradientBlur(color = colorTopRight, offsetX = 3 * screenWidth, offsetY = 0f)
    GradientBlur(color = colorBottomRight, offsetX = 2 * screenWidth, offsetY = 3 * screenHeight)
}

@Preview
@Composable
fun AuthBackgroundBlur(
    colorCenterLeft : Color = LocalCustomColorScheme.current.pinkAccent,
    colorTopRight: Color = LocalCustomColorScheme.current.purpleAccent
){
    val screenWidth = Size.width().toFloat()
    val screenHeight = Size.height().toFloat()

    GradientBlur(color = colorCenterLeft, offsetX = 0f, offsetY = screenHeight)
    GradientBlur(color = colorTopRight, offsetX = 3 * screenWidth, offsetY = 0f)
}
