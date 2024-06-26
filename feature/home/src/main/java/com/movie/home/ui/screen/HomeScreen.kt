package com.movie.home.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.movie.annotations.Route
import com.movie.home.ui.component.LatestMovieItem
import com.movie.home.ui.component.NowPlayingList
import com.movie.home.ui.component.PopularMovieList
import com.movie.home.ui.component.TopRatedList
import com.movie.home.ui.component.TrendingList
import com.movie.home.ui.viewmodel.HomeUiState
import com.movie.home.ui.viewmodel.HomeViewModel
import com.ui.design.button.MovieButton
import com.ui.design.button.MovieButtonType
import com.ui.design.effect.ParallaxLazyColumn
import com.ui.design.loading.LoadingDialog
import com.ui.design.loading.shimmerEffect
import com.ui.design.spacing
import com.ui.design.theme.LocalEntryPadding

@Route(global = true)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onEvent: (HomeScreenUiEvent) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value

    Scaffold { _ ->
        HomeContent(
            modifier = Modifier, uiState = uiState, onEvent = onEvent
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun HomeContent(
    modifier: Modifier = Modifier, uiState: HomeUiState,
    onEvent: (HomeScreenUiEvent) -> Unit
) {
    ParallaxLazyColumn(modifier = modifier
        .padding(bottom = LocalEntryPadding.current.calculateBottomPadding())
        .fillMaxSize(),
        topItemHeight = (LocalConfiguration.current.screenHeightDp / 1.5).dp,
        collapseContent = {
            TopAppBar(title = {
                Text(text = "HOME", style = MaterialTheme.typography.titleLarge)
            })
        },
        background = {
            if (uiState.movieUiModel.latestMovie != null) {
                LatestMovieItem(
                    item = uiState.movieUiModel.latestMovie
                )
            }
        }, contentPadding = PaddingValues(
            bottom = spacing.genericBottomMargin
        ), foreground = {
            if (uiState.movieUiModel.latestMovie != null)
                Surface(color = Color.Transparent) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = uiState.movieUiModel.latestMovie?.title.orEmpty(),
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White,
                            modifier = Modifier
                                .padding(
                                    bottom = spacing.spaceNormal
                                )
                                .padding(
                                    horizontal = spacing.genericHorizontalMargin
                                )
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                        MovieButton(
                            label = "Explore", type = MovieButtonType.SMALL,
                            modifier = Modifier.padding(
                                bottom = spacing.spaceMedium
                            )
                        ) {
                            onEvent(
                                HomeScreenUiEvent.NavigateToMovieDetail(
                                    uiState.movieUiModel.latestMovie.id
                                )
                            )
                        }

                        Text(
                            text = uiState.movieUiModel.latestMovie?.overview.orEmpty(),
                            style = MaterialTheme.typography.titleLarge.copy(
                                textMotion = TextMotion.Animated
                            ),
                            color = Color.White,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(
                                    bottom = spacing.spaceBig
                                )
                                .padding(horizontal = spacing.genericHorizontalMargin)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            else {
                if (uiState.homeLoading.isLatestMovieLoading) {
                    HeaderShimmer()
                }
            }
        }, itemContent = {
            if (!uiState.movieUiModel.nowPlayingMovies.isNullOrEmpty()) {
                item("now_playing") {
                    NowPlayingList(
                        nowPlayingList = uiState.movieUiModel.nowPlayingMovies,
                        modifier = Modifier
                            .animateItemPlacement()
                            .padding(
                                top = spacing.spaceBig
                            ),
                        onClickDetail = {
                            onEvent(HomeScreenUiEvent.NavigateToMovieDetail(it))
                        }
                    )
                }
            } else {
                if (uiState.homeLoading.isNowPlayingMovieLoading) {
                    item {
                        ContentShimmer(
                            modifier = Modifier.padding(
                                top = spacing.spaceNormal
                            )
                        )
                    }
                }
            }
            if (!uiState.movieUiModel.trendingMovies.isNullOrEmpty()) {
                item("trending") {
                    TrendingList(
                        trendingList = uiState.movieUiModel.trendingMovies,
                        modifier = Modifier
                            .animateItemPlacement()
                            .padding(
                                top = spacing.spaceNormal
                            ),
                        onClickDetail = {
                            onEvent(HomeScreenUiEvent.NavigateToMovieDetail(it))
                        }
                    )
                }
            } else {
                if (uiState.homeLoading.isTrendingMovieLoading) {
                    item {
                        ContentShimmer(
                            modifier = Modifier.padding(
                                top = spacing.spaceNormal
                            )
                        )
                    }
                }
            }

            if (!uiState.movieUiModel.popularMovies.isNullOrEmpty()) {
                item("popular") {
                    PopularMovieList(
                        popularList = uiState.movieUiModel.popularMovies,
                        modifier = Modifier
                            .animateItemPlacement()
                            .padding(
                                top = spacing.spaceNormal
                            ),
                        onClickDetail = {
                            onEvent(HomeScreenUiEvent.NavigateToMovieDetail(it))
                        }
                    )
                }
            } else {
                if (uiState.homeLoading.isPopularMovieLoading) {
                    item {
                        ContentShimmer(
                            modifier = Modifier.padding(
                                top = spacing.spaceNormal
                            )
                        )
                    }
                }
            }

            if (!uiState.movieUiModel.topRatedMovies.isNullOrEmpty()) {
                item("top_rated") {
                    TopRatedList(
                        topRatedList = uiState.movieUiModel.topRatedMovies,
                        modifier = Modifier
                            .animateItemPlacement()
                            .padding(
                                top = spacing.spaceNormal
                            )
                    ) {
                        onEvent(HomeScreenUiEvent.NavigateToMovieDetail(it))
                    }
                }
            } else {
                if (uiState.homeLoading.isTopRatedMovieLoading) {
                    item {
                        ContentShimmer(
                            modifier = Modifier.padding(
                                top = spacing.spaceNormal
                            )
                        )
                    }
                }
            }
        })

}

@Composable
private fun HeaderShimmer() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                horizontal = spacing.genericHorizontalMargin
            )
            .height(300.dp)
            .shimmerEffect(
                shape = MaterialTheme.shapes.medium
            )
    )
}

@Composable
private fun ContentShimmer(
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
        contentPadding = PaddingValues(
            horizontal = spacing.genericHorizontalMargin
        )
    ) {
        repeat(5) {
            item {
                Box(
                    modifier = Modifier
                        .width(200.dp)
                        .height(150.dp)
                        .shimmerEffect(
                            shape = MaterialTheme.shapes.medium
                        )
                )
            }
        }
    }
}


sealed interface HomeScreenUiEvent {
    data class NavigateToMovieDetail(val movieId: Int) : HomeScreenUiEvent
}