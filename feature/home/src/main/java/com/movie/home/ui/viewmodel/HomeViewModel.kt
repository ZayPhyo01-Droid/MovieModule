package com.movie.home.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.common.base.BaseViewModel
import com.movie.api.movie.domain.usecase.GetLatestMovieUseCase
import com.movie.api.movie.domain.usecase.GetNowPlayingMovieUseCase
import com.movie.api.movie.domain.usecase.GetPopularMovieUseCase
import com.movie.api.movie.domain.usecase.GetTopRatedMovieUseCase
import com.movie.api.movie.domain.usecase.GetTrendingMovieUseCase
import com.movie.home.ui.model.MovieUiModel
import com.movie.ktor.util.handler.handle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTrendingMovieUseCase: GetTrendingMovieUseCase,
    private val nowPlayingMovieUseCase: GetNowPlayingMovieUseCase,
    private val topRatedMovieUseCase: GetTopRatedMovieUseCase,
    private val popularMovieUseCase: GetPopularMovieUseCase,
    private val latestMovieUseCase: GetLatestMovieUseCase
) : BaseViewModel<HomeUiState, HomeViewModelEvent>(
    initialEvent = HomeViewModelEvent.Idle, initialState = HomeUiState(isShimmer = true)
) {

    init {
        fetchTrendingMovie()
        fetchNowPlayingMovie()
        fetchTopRatedMovie()
        fetchPopularMovie()
        fetchLatestMovie()
    }

    private fun fetchTrendingMovie() {
        viewModelScope.launch {
            getTrendingMovieUseCase().handle(right = { trending ->
                _uiState.update {
                    it.copy(
                        movieUiModel = it.movieUiModel.copy(
                            trendingMovies = trending
                        )
                    )
                }
            })
        }
    }

    private fun fetchNowPlayingMovie() {
        viewModelScope.launch {
            nowPlayingMovieUseCase().handle(right = { nowPlaying ->
                _uiState.update {
                    it.copy(
                        movieUiModel = it.movieUiModel.copy(
                            nowPlayingMovies = nowPlaying
                        )
                    )
                }
            })
        }
    }

    private fun fetchTopRatedMovie() {
        viewModelScope.launch {
            topRatedMovieUseCase().handle(right = { topRated ->
                _uiState.update {
                    it.copy(
                        movieUiModel = it.movieUiModel.copy(
                            topRatedMovies = topRated
                        )
                    )
                }
            })
        }
    }

    private fun fetchPopularMovie() {
        viewModelScope.launch {
            popularMovieUseCase().handle(right = { popularMovie ->
                _uiState.update {
                    it.copy(
                        movieUiModel = it.movieUiModel.copy(
                            popularMovies = popularMovie
                        )
                    )
                }
            })
        }
    }

    private fun fetchLatestMovie() {
        viewModelScope.launch {
            latestMovieUseCase().handle(
                right = { latestMovie ->
                    if (latestMovie != null)
                        _uiState.update {
                            it.copy(
                                movieUiModel = it.movieUiModel.copy(
                                    latestMovie = latestMovie
                                )
                            )
                        }
                }
            )
        }
    }
}

data class HomeUiState(
    val isShimmer: Boolean,
    val movieUiModel: MovieUiModel = MovieUiModel()
)

sealed class HomeViewModelEvent {
    data class Error(val message: String) : HomeViewModelEvent()
    object Idle : HomeViewModelEvent()
}