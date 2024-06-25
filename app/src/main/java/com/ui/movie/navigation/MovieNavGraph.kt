package com.ui.movie.navigation

import androidx.collection.longLongMapOf
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.movie.api.movie.navigation.navMovieGraph
import com.movie.home.navigation.navHomeGraph
import com.movie.home.ui.screen.HomeScreenDestination
import com.movie.upcoming.navigation.navUpcomingGraph
import com.movie.upcoming.ui.screen.UpcomingScreenDestination
import com.ui.design.theme.LocalEntryPadding

@Composable
fun MovieNavGraph() {
    val navController = rememberNavController()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    val navigation = remember {
        Navigation(navController)
    }
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute =
        currentNavBackStackEntry?.destination?.route ?: HomeScreenDestination.destination()


    when (currentRoute) {
        HomeScreenDestination.destination() -> bottomBarState.value = true
        UpcomingScreenDestination.destination() -> bottomBarState.value = true
        else -> bottomBarState.value = false
    }
    Scaffold(
        bottomBar = {
            AnimatedVisibility(visible = bottomBarState.value,
                enter = slideInVertically { it },
                exit = slideOutVertically { it }
            ) {
                MovieBottomNavigation(navController = navController)
            }
        }
    ) {
        CompositionLocalProvider(LocalEntryPadding provides it) {
            NavHost(
                navController = navController,
                startDestination = HomeScreenDestination.destination(),
                builder = {
                    navHomeGraph(
                        globalNavigation = navigation.globalNavigation
                    )
                    navUpcomingGraph()
                    navMovieGraph(globalNavigation = navigation.globalNavigation)
                }
            )
        }
    }

}