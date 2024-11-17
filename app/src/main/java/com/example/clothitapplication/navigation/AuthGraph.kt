package com.example.clothitapplication.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.clothitapplication.presenter.screens.authScreens.loginScreen.LoginScreen
import com.example.clothitapplication.presenter.screens.splashScreen.SplashScreen


fun NavGraphBuilder.AuthGraph(navController: NavHostController) {
    navigation(route = Graph.AUTH, startDestination = NoAuthorizedClothitScreens.SplashScreen.name) {
        composable(NoAuthorizedClothitScreens.SplashScreen.name) {
             SplashScreen(navController)
        }
        composable(NoAuthorizedClothitScreens.LoginScreen.name) {
             LoginScreen()
        }
        composable(NoAuthorizedClothitScreens.RegisterScreen.name) {
            // RegisterScreen(navController)
        }
    }
}