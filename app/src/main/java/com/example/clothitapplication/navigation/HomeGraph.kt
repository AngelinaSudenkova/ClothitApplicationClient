package com.example.clothitapplication.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.clothitapplication.presenter.screens.friendsScreen.FriendsScreen
import com.example.clothitapplication.presenter.screens.outfitScreen.CreateOutfitViewModel
import com.example.clothitapplication.presenter.screens.messageScreen.MessageScreen
import com.example.clothitapplication.presenter.screens.profileScreen.ProfileScreen
import com.example.clothitapplication.presenter.screens.wardrobeScreen.WardrobeScreen


@Composable
fun HomeGraph(
    navController: NavHostController,
    authNavController: NavHostController
) {
    val outfitViewModel = hiltViewModel<CreateOutfitViewModel>()

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
        ItemGraph(navController = navController, outfitViewModel = outfitViewModel)
    }
}



