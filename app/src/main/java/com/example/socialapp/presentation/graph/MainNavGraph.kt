package com.example.socialapp.presentation.graph

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.socialapp.presentation.screens.main.MainScreen
import com.example.socialapp.presentation.screens.main.MainScreenViewModel
import com.example.socialapp.presentation.screens.profile.ProfileScreen
import com.example.socialapp.presentation.screens.profile.ProfileViewModel
import com.example.socialapp.presentation.theme.PurpleBlack

const val MAIN_GRAPH_TAG = "MAIN_GRAPH_TAG"

@Composable
fun MainNavGraph() {
    val listItems = listOf(
        BottomTabs.Home,
        BottomTabs.Search,
        BottomTabs.AddPost,
        BottomTabs.NOTIFICATION,
        BottomTabs.PROFILE,
    )

    val navHostController = rememberNavController()
    Scaffold(
        containerColor = PurpleBlack,
        bottomBar = {
            AppBottomNavigation(
                navController = navHostController,
                items = listItems,
                currentRoute = navHostController.currentBackStackEntry?.destination?.route ?: ""
            )
        },
    ) { innerPaddings ->
        NavHost(
            modifier = Modifier.padding(innerPaddings),
            navController = navHostController,
            startDestination = BottomTabs.Home.route
        ) {
            composable(BottomTabs.Home.route) {
                val viewModel: MainScreenViewModel = hiltViewModel()
                MainScreen(uiStateFlow = viewModel.uiState)
            }

            composable(BottomTabs.Search.route) {

            }

            composable(BottomTabs.AddPost.route) {

            }

            composable(BottomTabs.NOTIFICATION.route) {

            }

            composable(BottomTabs.PROFILE.route) {
                val viewModel: ProfileViewModel = hiltViewModel()
                ProfileScreen(
                    uiStateFlow = viewModel.uiState,
                    onInteraction = viewModel::onEvent
                )
            }
        }
    }
}