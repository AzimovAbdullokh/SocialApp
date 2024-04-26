package com.example.socialapp.presentation.graph

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.socialapp.presentation.theme.PurpleBlack

enum class BottomTabs(
    val icon: ImageVector,
    val title: String,
    val route: String
) {
    Home(
        icon = Icons.Default.Home,
        title = "Home",
        route = "home_screen"
    ),
    Search(
        icon = Icons.Default.Search,
        title = "Search",
        route = "search_screen"
    ),
    AddPost(
        icon = Icons.Default.Add,
        title = "AddPost",
        route = "add_post_screen"
    ),
    NOTIFICATION(
        icon = Icons.Default.Notifications,
        title = "Notification",
        route = "notification_screen"
    ),
    PROFILE(
        icon = Icons.Default.Person,
        title = "Profile",
        route = "profile_screen"
    ),
}

@Composable
fun AppBottomNavigation(
    navController: NavController,
    items: List<BottomTabs>,
    currentRoute: String,
) {
    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = PurpleBlack
    ) {
        items.forEach { bottomTabs ->
            AppBottomNavigationItem(
                modifier = Modifier.weight(1f).background(PurpleBlack),
                selected = currentRoute == bottomTabs.route,
                onClick = {
                    navController.navigate(bottomTabs.route) {
                        launchSingleTop = true
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                     }
                },
                icon = bottomTabs.icon
            )
        }
    }
}

private const val DEFAULT_ICON_SIZE = 56

@Composable
fun AppBottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    iconSize: Dp = DEFAULT_ICON_SIZE.dp,
) {
    val scale = if (selected) 1.5f else 1.0f

    val color = if (selected) Color.Blue
    else Color.LightGray

    val animatedScale: Float by animateFloatAsState(
        targetValue = scale,
        animationSpec = TweenSpec(
            durationMillis = 500,
            easing = FastOutSlowInEasing
        ), label = ""
    )
    val animatedColor by animateColorAsState(
        targetValue = color,
        animationSpec = TweenSpec(
            durationMillis = 500,
            easing = FastOutSlowInEasing
        ), label = ""
    )

    IconButton(
        onClick = onClick,
        modifier = modifier.size(iconSize)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = String(),
            tint = animatedColor,
            modifier = Modifier.scale(animatedScale)
        )
    }
}