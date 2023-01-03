package com.example.bugslayers.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.bugslayers.constants.Routes

@Composable
fun AppBar(navController: NavHostController){
    Column {
        TopAppBar(
            elevation = 4.dp,
            title = {
                Text("App Name", color = Color(0xffbec2c4))
            },
            backgroundColor =  Color(0xFF0073b1),
            navigationIcon = {
                IconButton(onClick = {
                    goBack(navController)
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color(0xffbec2c4)
                    )
                }
            })
    }
}

fun goBack(navController: NavHostController) {
    navController.popBackStack()
}
