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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.bugslayers.R
import com.example.bugslayers.constants.Routes
import com.example.bugslayers.utils.LogInUtil
import com.example.bugslayers.view.components.CustomButton
import com.example.bugslayers.view.components.CustomPasswordTextField
import com.example.bugslayers.view.components.CustomTextField

@Composable
fun LoginPage(navController: NavHostController) {
    val context = LocalContext.current
    var userName = remember {
        mutableStateOf("")
    }

    var password = remember {
        mutableStateOf("")
    }

    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.verticalScroll(scrollState).padding(top = 16.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.logo),
            modifier = Modifier.size(150.dp),
            contentDescription = "App Logo")

        Text(text = "Welcome Back!", fontSize=28.sp,
            color = Color(0xff41454f),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.offset(y= (-16).dp)
        )

        Text(text = "Log In to your account",
            color = Color(0xffbec2c4),
            modifier = Modifier.offset(y= (-16).dp)
        )

        Column(
            modifier = Modifier.padding(top = 36.dp, bottom = 24.dp)
        ) {
            CustomTextField(field = userName,
                labelText = "Username",
                placeHolderText = "username",
                textType = KeyboardType.Text)

            Spacer(modifier = Modifier.height(16.dp))

            CustomPasswordTextField(field = password,
                labelText = "Password",
                placeHolderText = "password",)
        }

        CustomButton(text = "Log In", onClick = {
            LogInUtil().logIn(userName.value, password.value, navController, context)
        })

        Row(modifier = Modifier.padding(top = 24.dp)) {
            Text(text = "Don't have an account? ", fontWeight = FontWeight.Light, color = Color(0xffc5c8cd))
            Text(text = "Sign Up",
                fontWeight = FontWeight.SemiBold ,
                color = Color(0xaa0073b1),
                modifier = Modifier.clickable {
                    gotoSignUpPage(navController);
                }
            )
        }

    }
}

fun gotoSignUpPage(navController: NavHostController){
    navController.navigate(Routes.SIGN_UP_ROUTE)
}
