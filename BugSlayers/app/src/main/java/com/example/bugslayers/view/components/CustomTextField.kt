package com.example.bugslayers.view.components

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    field: MutableState<String>,
    labelText: String,
    placeHolderText: String,
    textType: KeyboardType
){
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
        keyboardOptions = KeyboardOptions(keyboardType = textType),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
        singleLine = true,
        label = { Text(labelText) },
        placeholder = { Text(text = placeHolderText, color = Color.Gray) },
    )
}