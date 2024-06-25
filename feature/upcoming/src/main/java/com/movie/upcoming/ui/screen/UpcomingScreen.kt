package com.movie.upcoming.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import com.movie.annotations.Route
import com.movie.annotations.RouteArg
import com.movie.api.movie.domain.model.MovieModel
import com.movie.upcoming.ui.component.UpcomingList
import com.movie.upcoming.ui.viewmodel.UpcomingViewModel
import com.ui.design.theme.LocalEntryPadding


@OptIn(ExperimentalMaterial3Api::class)
@Route(global = true)
@Composable
internal fun UpcomingScreen(
    viewModel: UpcomingViewModel = hiltViewModel(),
    @RouteArg
    movieType: String
) {
    val uiState = viewModel.uiState.collectAsState().value
    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(
            topAppBarScrollBehavior.nestedScrollConnection
        ),
        topBar = {
            TopAppBar(title = {
                Text(text = "UPCOMING", style = MaterialTheme.typography.titleLarge)
            }, scrollBehavior = topAppBarScrollBehavior)
        }
    ) {
        if (uiState.loading) {
            // Loading
        } else {
            UpcomingContent(
                modifier = Modifier
                    .padding(it),
                upcomingMovies = uiState.upcomingMovies ?: emptyList()
            )
        }
    }
}


@Composable
fun UpcomingContent(
    modifier: Modifier = Modifier,
    upcomingMovies: List<MovieModel>
) {
    UpcomingList(upcoming = upcomingMovies, modifier = modifier)
}