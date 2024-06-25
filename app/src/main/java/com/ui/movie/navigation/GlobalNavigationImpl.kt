package com.ui.movie.navigation

import androidx.navigation.NavController
import com.core.navigation.GlobalNavigation
import com.movie.api.movie.ui.screen.MovieDetailScreenDestination

class GlobalNavigationImpl(private val navController: NavController) : GlobalNavigation {
    override fun navigateToMovieDetail(movieId: String) {
        navController.navigate(
            MovieDetailScreenDestination.route(movieId)
        )
    }

    override fun navigateUp() {
        navController.navigateUp()
    }
}