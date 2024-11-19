package com.example.clothitapplication.presenter.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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


@Composable
fun AnimatedGradientBlur(
    color: Color,
    initialOffsetX: Float,
    initialOffsetY: Float,
) {

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val animatedOffsetX = infiniteTransition.animateFloat(
        initialValue = initialOffsetX,
        targetValue = initialOffsetX + 500f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 8000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    val animatedOffsetY = infiniteTransition.animateFloat(
        initialValue = initialOffsetY,
        targetValue = initialOffsetY - 500f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 8000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val gradientBrush = Brush.radialGradient(
        colors = listOf(color.copy(alpha = 0.5f), color.copy(alpha = 0.0f)),
        center = Offset(animatedOffsetX.value, animatedOffsetY.value),
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

    AnimatedGradientBlur(color = colorCenterLeft, initialOffsetX = 0f, initialOffsetY = screenHeight)
    AnimatedGradientBlur(color = colorTopRight, initialOffsetX = 3 * screenWidth,  initialOffsetY = 0f)
}
