package com.movie.api.movie.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core.navigation.GlobalNavigation
import com.movie.api.movie.ui.screen.MovieDetailScreen
import com.movie.api.movie.ui.screen.MovieDetailScreenDestination

fun NavGraphBuilder.navMovieGraph(
    globalNavigation: GlobalNavigation
) {
    navMovieDetail(globalNavigation)
}

private fun NavGraphBuilder.navMovieDetail(
    globalNavigation: GlobalNavigation
) {
    composable(MovieDetailScreenDestination.destination()) {
        MovieDetailScreen(
            movieId = MovieDetailScreenDestination.movieId(
                it.arguments!!
            )
        )
    }
}