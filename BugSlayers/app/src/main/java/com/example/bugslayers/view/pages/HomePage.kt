package com.example.bugslayers.view.pages

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bugslayers.view.components.AppBar
import com.example.bugslayers.view.navigation.*

@Composable
fun HomePage(navController: NavHostController) {
    val homePageNavController = rememberNavController()
    Scaffold(
        topBar = { AppBar(navController = navController) },
        backgroundColor = Color.White,
        bottomBar = { BottomNavigation(navController = homePageNavController)}
    ) {
        NavigationGraph(navController = homePageNavController)
    }
}
