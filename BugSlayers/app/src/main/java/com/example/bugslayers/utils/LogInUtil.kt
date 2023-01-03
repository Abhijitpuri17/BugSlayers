package com.example.bugslayers.utils

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.bugslayers.constants.Routes

class LogInUtil {
    fun logIn(userName: String,
              password: String,
              navController: NavHostController,
              context: Context
    ) {
        val areCredentialsValid =
            LogInCredentialsValidator().validate(userName, password)
        if(areCredentialsValid){
            /**TODO
             * Log In Logic
             */
            navController.navigate(Routes.HOME_PAGE_ROUTE) {
                popUpTo(Routes.LOG_IN_ROUTE){
                    inclusive = true
                }
            }
        } else {
            /** TODO
             * show error
             */
            Toast.makeText(
                context,
                "Username and Password cannot be empty",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}

class LogInCredentialsValidator{
    fun validate(userName: String, password: String): Boolean{
        if(userName.isEmpty() || password.isEmpty()){
            return false;
        }
        return true ;
    }
}