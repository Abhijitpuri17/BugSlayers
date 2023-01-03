package com.example.bugslayers.view.navigation

import android.graphics.drawable.Icon
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bugslayers.R
import com.example.bugslayers.constants.Routes

sealed class BottomNavItem(var title: String, var icon: ImageVector, var screen_route: String){

    object Explore :
        BottomNavItem(
            title = "Home",
            icon= Icons.Filled.Explore,
            screen_route = Routes.EXPLORE_PAGE_ROUTE
        )
    object Notifications:
        BottomNavItem(
            "Notifications",
            Icons.Filled.Notifications,
            Routes.NOTIFICATIONS_PAGE_ROUTE
        )
    object Profile:
        BottomNavItem(
            "Post",
            Icons.Filled.Person,
            Routes.PROFILE_PAGE_ROUTE
        )
}
