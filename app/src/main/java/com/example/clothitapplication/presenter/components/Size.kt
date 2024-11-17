package com.example.clothitapplication.presenter.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

object Size {
    @Composable
    fun height(): Int {
        val configuration = LocalConfiguration.current
        return configuration.screenHeightDp
    }
    @Composable
    fun width(): Int {
        val configuration = LocalConfiguration.current
        return configuration.screenWidthDp
    }
}