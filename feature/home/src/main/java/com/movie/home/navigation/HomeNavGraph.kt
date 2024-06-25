package com.movie.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core.navigation.GlobalNavigation
import com.movie.home.ui.screen.HomeScreen
import com.movie.home.ui.screen.HomeScreenDestination
import com.movie.home.ui.screen.HomeScreenUiEvent

fun NavGraphBuilder.navHomeGraph(
    globalNavigation: GlobalNavigation
) {
    navHome(globalNavigation)
}

private fun NavGraphBuilder.navHome(
    globalNavigation: GlobalNavigation
) {
    composable(HomeScreenDestination.destination()) {
        HomeScreen {
            when (it) {
                is HomeScreenUiEvent.NavigateToMovieDetail -> {
                    globalNavigation.navigateToMovieDetail(it.movieId.toString())
                }
            }
        }
    }
}