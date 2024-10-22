package com.example.clothitapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.clothitapplication.screens.friendsScreen.FriendsScreen
import com.example.clothitapplication.screens.messageScreen.MessageScreen
import com.example.clothitapplication.screens.profileScreen.ProfileScreen
import com.example.clothitapplication.screens.wardrobeScreen.WardrobeScreen


@Composable
fun HomeGraph(
    navController: NavHostController,
    authNavController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomNavigationScreens.WardrobeScreen.name
    ) {
        composable(route = BottomNavigationScreens.WardrobeScreen.name) {
            WardrobeScreen(navController)
        }
        composable(route = BottomNavigationScreens.FriendsScreen.name) {
            FriendsScreen(navController)
        }
        composable(route = BottomNavigationScreens.MessagesScreen.name) {
            MessageScreen(navController)
        }
        composable(route = BottomNavigationScreens.ProfileScreen.name) {
            ProfileScreen(navController, authNavController)
        }
        // ItemGraph(navController = navController)
    }
}



