package com.example.socialapp.app

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import com.example.socialapp.presentation.graph.SocialAppNavGraph
import com.example.socialapp.presentation.screens.utils.emptyString
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun SocialMainApp(
    destinationFlow: SharedFlow<Pair<String, Boolean>>,
    destinationMain: String = emptyString(),
) {
    val navController = rememberNavController()
    val (routeName, isBackStackClear) = destinationFlow.collectAsState(initial = "" to false).value

    if (destinationMain.isEmpty()) {
        if (routeName.isNotEmpty()) {
            navController.navigate(routeName) {
                if (isBackStackClear) popUpTo(0)
            }
        }
    } else {
        navController.navigate(destinationMain) {
            popUpTo(0)
        }
    }

    Surface {
        SocialAppNavGraph(
            navController = navController
        )
    }
}