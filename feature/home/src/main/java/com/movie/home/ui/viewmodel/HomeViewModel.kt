package com.movie.home.ui.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.common.base.BaseViewModel
import com.movie.api.movie.domain.usecase.GetLatestMovieUseCase
import com.movie.api.movie.domain.usecase.GetNowPlayingMovieUseCase
import com.movie.api.movie.domain.usecase.GetPopularMovieUseCase
import com.movie.api.movie.domain.usecase.GetTopRatedMovieUseCase
import com.movie.api.movie.domain.usecase.GetTrendingMovieUseCase
import com.movie.home.ui.model.MovieUiModel
import com.movie.network.handler.handle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
    initialEvent = HomeViewModelEvent.Idle, initialState = HomeUiState()
) {

    private suspend fun delayLoading() {
        delay(3000)
    }

    init {
        fetchTrendingMovie()
        fetchNowPlayingMovie()
        fetchTopRatedMovie()
        fetchPopularMovie()
        fetchLatestMovie()
    }

    private fun fetchTrendingMovie() {
        _uiState.update {
            it.copy(
                homeLoading = it.homeLoading.copy(
                    isTrendingMovieLoading = true
                )
            )
        }
        viewModelScope.launch {
            delayLoading()
            getTrendingMovieUseCase().handle(
                terminate = {
                    _uiState.update {
                        it.copy(
                            homeLoading = it.homeLoading.copy(
                                isTrendingMovieLoading = false
                            )
                        )
                    }
                },
                left = {
                    Log.d("error", it.message.toString())
                }, right = { trending ->
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
        _uiState.update {
            it.copy(
                homeLoading = it.homeLoading.copy(
                    isNowPlayingMovieLoading = true
                )
            )
        }
        viewModelScope.launch {
            delayLoading()
            nowPlayingMovieUseCase().handle(
                terminate = {
                    _uiState.update {
                        it.copy(
                            homeLoading = it.homeLoading.copy(
                                isNowPlayingMovieLoading = false
                            )
                        )
                    }
                }, right = { nowPlaying ->
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
        _uiState.update {
            it.copy(
                homeLoading = it.homeLoading.copy(
                    isTopRatedMovieLoading = true
                )
            )
        }
        viewModelScope.launch {
            delayLoading()
            topRatedMovieUseCase().handle(
                terminate = {
                    _uiState.update {
                        it.copy(
                            homeLoading = it.homeLoading.copy(
                                isTopRatedMovieLoading = false
                            )
                        )
                    }
                },
                right = { topRated ->
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
        _uiState.update {
            it.copy(
                homeLoading = it.homeLoading.copy(
                    isPopularMovieLoading = true
                )
            )
        }
        viewModelScope.launch {
            delayLoading()
            popularMovieUseCase().handle(
                terminate = {
                    _uiState.update {
                        it.copy(
                            homeLoading = it.homeLoading.copy(
                                isPopularMovieLoading = false
                            )
                        )
                    }
                },
                right = { popularMovie ->
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
        _uiState.update {
            it.copy(
                homeLoading = it.homeLoading.copy(
                    isLatestMovieLoading = true
                )
            )
        }
        viewModelScope.launch {
            delayLoading()
            latestMovieUseCase().handle(
                terminate = {
                    _uiState.update {
                        it.copy(
                            homeLoading = it.homeLoading.copy(
                                isLatestMovieLoading = false
                            )
                        )
                    }
                },
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
    val movieUiModel: MovieUiModel = MovieUiModel(),
    val homeLoading: HomeLoading = HomeLoading()
) {
    data class HomeLoading(
        val isLatestMovieLoading: Boolean = false,
        val isNowPlayingMovieLoading: Boolean = false,
        val isTrendingMovieLoading: Boolean = false,
        val isTopRatedMovieLoading: Boolean = false,
        val isPopularMovieLoading: Boolean = false,
    )
}

sealed class HomeViewModelEvent {
    data class Error(val message: String) : HomeViewModelEvent()
    object Idle : HomeViewModelEvent()
}