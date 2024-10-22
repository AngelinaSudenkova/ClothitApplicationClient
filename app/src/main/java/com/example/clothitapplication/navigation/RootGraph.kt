package com.example.clothitapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun RootGraph() {
    val authNavController = rememberNavController()

    NavHost(
        navController = authNavController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        AuthGraph(authNavController)
        composable(route = Graph.HOME) {
            HomeScreen(authNavController = authNavController)
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val ITEM = "item_graph"
    const val OUTFIT = "outfit_graph"
    const val AUTH = "auth_graph"
}