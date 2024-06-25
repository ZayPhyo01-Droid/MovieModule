package com.movie.upcoming.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.movie.upcoming.ui.screen.UpcomingScreen
import com.movie.upcoming.ui.screen.UpcomingScreenDestination


fun NavGraphBuilder.navUpcomingGraph() {
    navUpcoming()
}

private fun NavGraphBuilder.navUpcoming() {

    composable(UpcomingScreenDestination.destination()) {
        UpcomingScreen(movieType = "")
    }
}