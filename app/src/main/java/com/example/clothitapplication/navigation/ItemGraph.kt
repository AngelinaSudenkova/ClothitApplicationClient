package com.example.clothitapplication.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.ItemGraph(navController: NavController){
    navigation(route = Graph.ITEM, startDestination = AuthorizedClothitScreens.ItemScreen.name){
        composable(route = AuthorizedClothitScreens.ItemScreen.name ){
            //ItemScreen
        }
    }
}