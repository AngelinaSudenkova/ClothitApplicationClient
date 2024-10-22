package com.example.clothitapplication.screens.splashScreen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.clothitapplication.navigation.Graph
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }

    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LaunchedEffect(key1 = true) {
                scale.animateTo(
                    targetValue = 0.9f,
                    animationSpec = tween(durationMillis = 800,
                        easing = {
                            OvershootInterpolator(8f)
                                .getInterpolation(it)
                        })
                )
                delay(2000L)
                navController.navigate(Graph.HOME){
                    popUpTo(Graph.ROOT){
                        inclusive = true
                    }
                }
            }
            Text(text = "Splash Screen")
            Icon(Icons.Filled.Image, contentDescription = "Image",
                modifier = Modifier.scale(scale.value).background(Color(0xFF00FF00)))
        }
    }
}