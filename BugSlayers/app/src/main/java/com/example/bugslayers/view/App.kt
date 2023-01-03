package com.example.bugslayers.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bugslayers.constants.Routes
import com.example.bugslayers.view.pages.HomePage
import com.example.bugslayers.view.pages.LoginPage
import com.example.bugslayers.view.pages.SignUpPage

@Composable
fun App(){
    val navController = rememberNavController()

    NavHost(navController, startDestination = Routes.HOME_PAGE_ROUTE) {
        composable(Routes.LOG_IN_ROUTE) { LoginPage(navController)}
        composable(Routes.SIGN_UP_ROUTE) { SignUpPage(navController) }
        composable(Routes.HOME_PAGE_ROUTE){
            HomePage(navController)
        }
    }
}