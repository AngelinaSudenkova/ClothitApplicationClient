package com.example.clothitapplication.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.clothitapplication.presenter.components.BottomNavigationBarCustom
import dev.chrisbanes.haze.HazeState

@Composable
fun HomeScreen(
    authNavController: NavHostController,
    navController: NavHostController = rememberNavController()
) {
    val hazeState = remember {
        HazeState()
    }
    Scaffold(
        bottomBar = {
            BottomNavigationBarCustom(
                hazeState = hazeState,
                navController = navController
            )
        }
    ) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            HomeGraph(navController = navController, authNavController = authNavController)
        }
    }
}