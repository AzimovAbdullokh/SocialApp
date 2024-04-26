package com.example.socialapp.app

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import com.example.socialapp.presentation.graph.SocialAppNavGraph
import com.example.socialapp.presentation.theme.PurpleBlack
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.Flow

@Composable
fun SocialMainApp(
    destinationFlow: Flow<Pair<String, Boolean>>,
) {
    val uiController = rememberSystemUiController()

    val navController = rememberNavController()
    val (routeName, isBackStackClear) = destinationFlow.collectAsState(initial = "" to false).value

    if (routeName.isNotEmpty()) {
        navController.navigate(routeName) {
            if (isBackStackClear) popUpTo(0)
        }
    }

    Surface {
        SocialAppNavGraph(
            navController = navController
        )
    }

    SideEffect {
        uiController.setStatusBarColor(PurpleBlack)
        uiController.setNavigationBarColor(PurpleBlack)
    }
}