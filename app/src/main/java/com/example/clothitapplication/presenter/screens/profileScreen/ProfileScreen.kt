package com.example.clothitapplication.presenter.screens.profileScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.clothitapplication.navigation.Graph


@Composable
fun ProfileScreen(
    navController: NavHostController,
    authNavController: NavHostController
) {

    Surface(
        modifier = Modifier.padding(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Profile Screen")
            Button(onClick = {
                authNavController.navigate(Graph.ROOT) {
                    popUpTo(Graph.ROOT) {
                        inclusive = true
                    }
                }
            }) {
                Text("Logout")
            }
        }
    }
}
