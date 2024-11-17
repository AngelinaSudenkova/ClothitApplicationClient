package com.example.clothitapplication.presenter.screens.splashScreen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.clothitapplication.R
import com.example.clothitapplication.navigation.Graph
import com.example.clothitapplication.navigation.NoAuthorizedClothitScreens
import com.example.clothitapplication.presenter.components.SplashBackgroundBlur
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }
    Box(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        SplashBackgroundBlur()
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
                navController.navigate(NoAuthorizedClothitScreens.LoginScreen.name) {
                    popUpTo(Graph.ROOT) {
                        inclusive = true
                    }
                }
            }
            Icon(
                painterResource(id = R.drawable.clothit_logo),
                contentDescription = "Clothit Logo",
                modifier = Modifier.scale(scale.value),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}