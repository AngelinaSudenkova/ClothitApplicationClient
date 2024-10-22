package com.example.clothitapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.clothitapplication.navigation.RootGraph
import com.example.clothitapplication.ui.theme.ClothitApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClothitApplicationTheme {
                ClothitApp()
            }
        }
    }
}

@Composable
fun ClothitApp() {
       RootGraph()
    }


