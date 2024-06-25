package com.ui.movie.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.movie.home.ui.screen.HomeScreenDestination
import com.movie.upcoming.ui.screen.UpcomingScreenDestination

sealed class Screen(val route: String, val name: String) {
    object Home : Screen(HomeScreenDestination.destination(), "Home")
    object Upcoming : Screen(UpcomingScreenDestination.destination(), "Upcoming")
}

@Composable
fun MovieBottomNavigation(
    navController: NavController
) {
    val items = listOf(
        Screen.Home,
        Screen.Upcoming
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.primary,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEachIndexed { i, screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            BottomNavigationItem(
                icon = {
                    Icon(
                        if (i == 0) {
                            if (isSelected)
                                Icons.Filled.Home
                            else Icons.Outlined.Home
                        } else {
                            if (isSelected) Icons.Filled.DateRange else Icons.Outlined.DateRange
                        },
                        contentDescription = null
                    )
                },
                label = { Text(screen.name, style = MaterialTheme.typography.labelMedium) },
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}