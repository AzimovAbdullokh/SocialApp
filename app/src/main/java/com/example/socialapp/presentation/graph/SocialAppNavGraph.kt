package com.example.socialapp.presentation.graph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.socialapp.presentation.auth.graph.AUTH_GRAPH_TAG
import com.example.socialapp.presentation.auth.graph.AuthNavGraph
import com.example.socialapp.presentation.splash.SPLASH_APP_GRAPH
import com.example.socialapp.presentation.splash.SplashScreen
import com.example.socialapp.presentation.splash.SplashViewModel

@Composable
fun SocialAppNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = SPLASH_APP_GRAPH
    ) {
        composable(SPLASH_APP_GRAPH) {
            val viewModel: SplashViewModel = hiltViewModel()
            SplashScreen()
        }
        composable(AUTH_GRAPH_TAG) {
            AuthNavGraph()
        }
        composable(MAIN_GRAPH_TAG) {
            MainNavGraph()
        }
    }
}