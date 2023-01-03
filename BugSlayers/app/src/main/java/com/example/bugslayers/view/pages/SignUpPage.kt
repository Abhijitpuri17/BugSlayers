package com.example.bugslayers.view.pages

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.example.bugslayers.R
import com.example.bugslayers.view.components.CustomButton
import com.example.bugslayers.view.components.CustomPasswordTextField
import com.example.bugslayers.view.components.CustomTextField

@Composable
fun SignUpPage(navController: NavHostController) {

    var name = remember {
        mutableStateOf("")
    }

    var email = remember {
        mutableStateOf("")
    }

    var userName = remember {
        mutableStateOf("")
    }

    var password = remember {
        mutableStateOf("")
    }

    var confirmPassword = remember {
        mutableStateOf("")
    }

    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Image(painter = painterResource(id = R.drawable.logo),
            modifier = Modifier.size(150.dp),
            contentDescription = "App Logo")

        Text(text = "Welcome!", fontSize=36.sp,
            color = Color(0xff41454f),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.offset(y= (-16).dp)
        )

        Text(text = "Create your account",
            color = Color(0xffbec2c4),
            modifier = Modifier.offset(y= (-16).dp)
        )

        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            CustomTextField(field = name,
                labelText = "Name",
                placeHolderText = "name",
                textType = KeyboardType.Text)

            Spacer(modifier = Modifier.height(6.dp))

            CustomTextField(field = email,
                labelText = "Email",
                placeHolderText = "email",
                textType = KeyboardType.Email)

            Spacer(modifier = Modifier.height(6.dp))

            CustomTextField(field = userName,
                labelText = "Email",
                placeHolderText = "email",
                textType = KeyboardType.Text)

            Spacer(modifier = Modifier.height(6.dp))

            CustomPasswordTextField(field = password,
                labelText = "Password",
                placeHolderText = "password",)

            Spacer(modifier = Modifier.height(6.dp))

            CustomPasswordTextField(field = confirmPassword,
                labelText = "Confirm Password",
                placeHolderText = "password")
        }

        CustomButton(text = "Sign Up", onClick = {})
        
        Row(modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "Already have an account? ", fontWeight = FontWeight.Light, color = Color(0xffc5c8cd))
            Text(text = "Log In",
                fontWeight = FontWeight.SemiBold ,
                color = Color(0xaa0073b1),
                modifier = Modifier.clickable {
                    gotoLogInPage(navController)
                }
            )
        }
        
    }
}

fun gotoLogInPage(navController: NavHostController){
    navController.popBackStack()
}