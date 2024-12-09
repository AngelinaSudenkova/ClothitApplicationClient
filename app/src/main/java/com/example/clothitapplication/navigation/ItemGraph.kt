package com.example.clothitapplication.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.clothitapplication.presenter.screens.outfitScreen.AddItemsToOutfitScreen
import com.example.clothitapplication.presenter.screens.outfitScreen.CreateOutfitScreen
import com.example.clothitapplication.presenter.screens.outfitScreen.CreateOutfitViewModel
import com.example.clothitapplication.presenter.screens.itemScreen.ItemScreen
import com.example.clothitapplication.presenter.screens.itemScreen.UpdateItemScreen
import com.example.clothitapplication.presenter.screens.outfitScreen.UpdateOutfitScreen


fun NavGraphBuilder.ItemGraph(
    navController: NavController,
    outfitViewModel: CreateOutfitViewModel
) {
    navigation(route = Graph.ITEM, startDestination = AuthorizedClothitScreens.ItemScreen.name) {
        composable(route = AuthorizedClothitScreens.ItemScreen.name) {
            ItemScreen(navController = navController)
        }

        composable(route = "${AuthorizedClothitScreens.UpdateItemScreen.name}/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")?.toInt() ?: 0
            UpdateItemScreen(navController = navController, itemId = itemId)
        }

        composable(route = "${AuthorizedClothitScreens.CreateOutfitScreen.name}?/{outfitId}") { backStackEntry ->
            val outfitId = backStackEntry.arguments?.getString("outfitId")?.toInt()
            CreateOutfitScreen(navController = navController, outfitViewModel = outfitViewModel,
                outfitId = outfitId)
        }

        composable(route = "${AuthorizedClothitScreens.AddItemsToOutfitScreen.name}/{itemCategory}") { backStackEntry ->
            val itemCategory = backStackEntry.arguments?.getString("itemCategory") ?: ""
            AddItemsToOutfitScreen(
                itemCategory = itemCategory,
                navController = navController,
                outfitViewModel = outfitViewModel
            )
        }
        composable(route = "${AuthorizedClothitScreens.UpdateOutfitScreen.name}/{outfitId}") { backStackEntry ->
            val outfitId = backStackEntry.arguments?.getString("outfitId")?.toInt() ?: 0
            UpdateOutfitScreen(navController = navController, outfitId = outfitId)
        }
    }
}