package com.example.socialapp.presentation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.socialapp.presentation.auth.graph.AUTH_GRAPH_TAG
import com.example.socialapp.presentation.auth.graph.AuthNavGraph
import com.example.socialapp.presentation.screens.main.MAIN_GRAPH_TAG
import com.example.socialapp.presentation.screens.main.screens.MainScreen

@Composable
fun SocialAppNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = AUTH_GRAPH_TAG,
    ) {
        composable(AUTH_GRAPH_TAG) {
            AuthNavGraph()
        }
        composable(MAIN_GRAPH_TAG) {
            MainScreen()
        }
    }
}