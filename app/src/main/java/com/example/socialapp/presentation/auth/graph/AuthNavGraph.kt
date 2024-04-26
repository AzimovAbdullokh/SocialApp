package com.example.socialapp.presentation.auth.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.socialapp.presentation.auth.login.LoginDestination
import com.example.socialapp.presentation.auth.login.LoginMainScreen
import com.example.socialapp.presentation.auth.login.LoginViewModel
import com.example.socialapp.presentation.auth.register.RegisterDestination
import com.example.socialapp.presentation.auth.register.RegisterMainScreen
import com.example.socialapp.presentation.auth.register.RegisterViewModel

const val AUTH_GRAPH_TAG = "AUTH_GRAPH_TAG"

@Composable
fun AuthNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginDestination.route,
    ) {
        composable(LoginDestination.route) {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginMainScreen(
                onInteraction = viewModel::onInteraction,
                uiStateFlow = viewModel.uiState,
                onNavigateSignUp = { navController.navigate(RegisterDestination.route) },
                userInfoStateFlow = viewModel.userInfoState,
            )
        }
        composable(RegisterDestination.route) {
            val viewModel: RegisterViewModel = hiltViewModel()
            val navCommand by viewModel.navControllerFlow.collectAsStateWithLifecycle(
                initialValue = null,
            )

            LaunchedEffect(key1 = navCommand) {
                if (navCommand != null) navController.navigate(navCommand!!)
            }
            RegisterMainScreen(
                uiStateFlow = viewModel.uiState,
                onInteraction = viewModel::onInteraction,
                onNavigateToLogin = { navController.navigate(LoginDestination.route) },
                userInfoStateFlow = viewModel.userInfoState
            )
        }
    }
}