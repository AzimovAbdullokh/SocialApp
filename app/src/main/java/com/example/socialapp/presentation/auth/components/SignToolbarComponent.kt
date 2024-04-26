package com.example.socialapp.presentation.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.socialapp.presentation.theme.PurpleBlack

@Composable
fun SignToolbarComponent(
    modifier: Modifier = Modifier,
    toolbarTitle: String,
) {
    Column(
        modifier = modifier
            .background(PurpleBlack)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 10.dp),
    ) {
        Text(
            text = toolbarTitle,
            fontWeight = FontWeight.SemiBold,
            fontSize = 32.sp,
            color = Color.White,
            lineHeight = 40.sp,
            modifier = Modifier.padding(start = 14.dp, top = 24.dp)
        )
    }
}

@Preview
@Composable
fun SignToolbarComponentPreview() {
    MaterialTheme {
        SignToolbarComponent(toolbarTitle = "Sign Up")
    }
}