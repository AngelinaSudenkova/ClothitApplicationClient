package com.example.clothitapplication.navigation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.clothitapplication.presenter.components.BottomNavigationBarCustom
import dev.chrisbanes.haze.HazeState
import androidx.compose.runtime.LaunchedEffect

@Composable
fun HomeScreen(
    authNavController: NavHostController,
    navController: NavHostController = rememberNavController()
) {
    val hazeState = remember {
        HazeState()
    }

    var showBottomBar by remember { mutableStateOf(true) }

    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            showBottomBar = destination.route in BottomNavigationScreens.entries.map { it.name }}
        }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBarCustom(
                    hazeState = hazeState,
                    navController = navController
                )
            }
        }
    ) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            HomeGraph(navController = navController, authNavController = authNavController)
        }
    }
}