package com.example.socialapp.presentation.auth.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialapp.presentation.theme.PurpleGrey40

@Composable
fun SocialTextFieldComponent(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
) {
    TextField(
        modifier = modifier
            .padding(top = 15.dp)
            .fillMaxWidth(),
        value = text,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                color = PurpleGrey40,
                fontWeight = FontWeight.Normal,
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = PurpleGrey40,
            unfocusedIndicatorColor = PurpleGrey40,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
        ),
    )
}

@Preview
@Composable
fun SocialTextFieldComponentPreview() {
    SocialTextFieldComponent(
        text = "",
        label = "First name",
        onValueChange = {},
        isPassword = true,
    )
}