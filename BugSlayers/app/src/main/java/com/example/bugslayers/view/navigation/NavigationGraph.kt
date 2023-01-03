package com.example.bugslayers.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bugslayers.view.pages.Explore
import com.example.bugslayers.view.pages.Notifications
import com.example.bugslayers.view.pages.Profile

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Profile.screen_route) {
        composable(BottomNavItem.Explore.screen_route) {
            Explore()
        }
        composable(BottomNavItem.Notifications.screen_route) {
            Notifications()
        }
        composable(BottomNavItem.Profile.screen_route) {
            Profile()
        }
    }
}