package com.movie.api.movie.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.common.base.BaseViewModel
import com.common.event.Event
import com.movie.api.movie.domain.model.MovieModel
import com.movie.api.movie.domain.usecase.GetMovieDetailUseCase
import com.movie.network.handler.handle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : BaseViewModel<MovieDetailUiState, MovieDetailViewModelEvent>(
    initialState = MovieDetailUiState(),
    initialEvent = MovieDetailViewModelEvent.Idle
) {

    private val movieId: String = savedStateHandle.get<String>("movieId")!!

    init {
        _uiState.update {
            it.copy(
                loading = true
            )
        }
        viewModelScope.launch {
            getMovieDetailUseCase(movieId).handle(
                left = {
                    _event.value = Event(
                        MovieDetailViewModelEvent.Error(it.message.orEmpty())
                    )
                },
                terminate = {
                    _uiState.update {
                        it.copy(
                            loading = false
                        )
                    }
                },
                right = { model ->
                    _uiState.update {
                        it.copy(
                            movieDetail = model
                        )
                    }
                }
            )
        }
    }
}

data class MovieDetailUiState(
    val loading: Boolean = false,
    val movieDetail: MovieModel? = null
)

sealed class MovieDetailViewModelEvent {
    object Idle : MovieDetailViewModelEvent()
    data class Error(val message: String) : MovieDetailViewModelEvent()
}