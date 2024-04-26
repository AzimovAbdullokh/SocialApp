package com.example.socialapp.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.socialapp.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

const val SPLASH_APP_GRAPH = "SPLASH_APP_GRAPH"

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
) {
    val uiController = rememberSystemUiController()

    LoadingAnimationLottie(
        modifier = modifier.fillMaxSize()
            .background(Color.White)
    )

    SideEffect {
        uiController.setStatusBarColor(Color.White)
        uiController.setNavigationBarColor(Color.White)
    }
}

@Composable
fun LoadingAnimationLottie(
    modifier: Modifier = Modifier,
) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.splash_lottie
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
    )

    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier,
    )
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}