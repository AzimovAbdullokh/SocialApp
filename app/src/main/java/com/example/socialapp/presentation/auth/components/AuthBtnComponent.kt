package com.example.socialapp.presentation.auth.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialapp.presentation.theme.SocialBlue

@Composable
fun AuthBtnComponent(
    modifier: Modifier = Modifier,
    btnText: String,
    onClick: () -> Unit,
    isProgressBarShow: Boolean,
) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = SocialBlue),
        shape = RoundedCornerShape(24.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        if (isProgressBarShow) CircularProgressIndicator(
            color = Color.White,
            modifier = Modifier
                .fillMaxHeight()
                .size(32.dp)
        )
        else Text(text = btnText)
    }
}

@Preview
@Composable
fun AuthBtnComponentPreview() {
    MaterialTheme {
        AuthBtnComponent(
            btnText = "Sign Up",
            onClick = {},
            isProgressBarShow = false
        )
    }
}