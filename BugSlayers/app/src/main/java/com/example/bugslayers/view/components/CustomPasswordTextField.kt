package com.example.bugslayers.view.components

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.bugslayers.R;

@Composable
fun CustomPasswordTextField(
    field: MutableState<String>,
    labelText: String,
    placeHolderText: String,
){
    var passwordVisible by remember {
        mutableStateOf(false)
    }

    val focusManager = LocalFocusManager.current
    TextField(
        value = field.value,
        onValueChange = {
            field.value = it },
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedLabelColor = Color.Gray,
            focusedLabelColor = Color(0xff0073b1),
            cursorColor = Color(0xff0073b1)
        ),
        modifier = Modifier.border(width = 1.dp,
            color =  Color(0xffe9eaea),
            shape = RoundedCornerShape(10.dp)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.moveFocus(FocusDirection.Down)
        }),

        trailingIcon = {
            IconButton(onClick = {
                passwordVisible = !passwordVisible
            }) {
                if(passwordVisible){
                    Icon(imageVector = Icons.Filled.VisibilityOff,
                        tint = Color.Gray,
                        contentDescription = "")
                } else {
                    Icon(imageVector = Icons.Filled.Visibility,
                        tint = Color.Gray,
                        contentDescription = "")
                }
            }
        },


        singleLine = true,
        label = { Text(labelText) },
        placeholder = { Text(text = placeHolderText, color = Color.Gray) },
        visualTransformation = if(passwordVisible) VisualTransformation.None else
            PasswordVisualTransformation(),
    )
}