package com.example.socialapp.presentation.auth.graph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.socialapp.presentation.auth.login.LoginDestination
import com.example.socialapp.presentation.auth.login.LoginScreen
import com.example.socialapp.presentation.auth.login.LoginViewModel
import com.example.socialapp.presentation.auth.register.RegisterDestination
import com.example.socialapp.presentation.auth.register.RegisterScreen
import com.example.socialapp.presentation.auth.register.RegisterViewModel

const val AUTH_GRAPH_TAG = "AUTH_GRAPH_TAG"

@Composable
fun AuthNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginDestination.route
    ) {
        composable(LoginDestination.route) {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                onInteraction = viewModel::onInteraction,
                uiStateFlow = viewModel.uiState,
                onNavigateSignUp = { navController.navigate(RegisterDestination.route) }
            )
        }
        composable(RegisterDestination.route) {
            val viewModel: RegisterViewModel = hiltViewModel()
            RegisterScreen(
                onInteraction = viewModel::onInteraction,
                uiStateFlow = viewModel.uiState,
                onNavigateToLogin = { navController.navigate(LoginDestination.route) }
            )
        }
    }
}