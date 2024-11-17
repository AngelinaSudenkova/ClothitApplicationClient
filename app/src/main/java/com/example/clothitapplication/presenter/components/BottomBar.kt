package com.example.clothitapplication.presenter.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.clothitapplication.presenter.components.BottomBarTab.Friends
import com.example.clothitapplication.presenter.components.BottomBarTab.Messages
import com.example.clothitapplication.presenter.components.BottomBarTab.Profile
import com.example.clothitapplication.presenter.components.BottomBarTab.Wardrobe
import com.example.clothitapplication.navigation.BottomNavigationScreens


sealed class BottomBarTab(val title: String, val icon: ImageVector, val color: Color) {
    data object Wardrobe : BottomBarTab(
        BottomBarTabType.Wardrobe.name,
        Icons.Filled.Checkroom,
        Colors.PurpleAccent
    )

    data object Friends : BottomBarTab(
        BottomBarTabType.Friends.name,
        Icons.Default.Group,
        Colors.YellowAccent
    )

    data object Messages : BottomBarTab(
        BottomBarTabType.Messages.name,
        Icons.Default.Email,
        Colors.BlueAccent
    )

    data object Profile : BottomBarTab(
        BottomBarTabType.Profile.name,
        Icons.Default.Person,
        Colors.PinkAccent
    )

    fun getRoute(tab: BottomBarTab): String {
        return when (tab) {
            Wardrobe -> BottomNavigationScreens.WardrobeScreen.name
            Friends -> BottomNavigationScreens.FriendsScreen.name
            Messages -> BottomNavigationScreens.MessagesScreen.name
            Profile -> BottomNavigationScreens.ProfileScreen.name
        }
    }
}

val tabs = listOf(Wardrobe, Friends, Messages, Profile)


enum class BottomBarTabType {
    Wardrobe,
    Friends,
    Messages,
    Profile
}

object Colors {
    val PurpleAccent = Color(0xFF930BFD)
    val YellowAccent = Color(0xFFFFBD47)
    val BlueAccent = Color(0xFF8BDFFF)
    val PinkAccent = Color(0xFFFF1464)
}


