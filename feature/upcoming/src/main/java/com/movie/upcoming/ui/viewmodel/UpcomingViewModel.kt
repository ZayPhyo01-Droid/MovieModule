package com.movie.upcoming.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.common.base.BaseViewModel
import com.common.event.Event
import com.movie.api.movie.domain.model.MovieModel
import com.movie.network.handler.handle
import com.movie.upcoming.domain.usecase.GetUpcomingMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingViewModel @Inject constructor(
    getUpcomingMovieUseCase: GetUpcomingMovieUseCase
) :
    BaseViewModel<UpcomingUiState, UpcomingViewModelEvent>(
        initialEvent = UpcomingViewModelEvent.Idle,
        initialState = UpcomingUiState()
    ) {

    init {
        viewModelScope.launch {
            getUpcomingMovieUseCase().handle(
                right = { upcoming ->
                    _uiState.update {
                        it.copy(
                            upcomingMovies = upcoming
                        )
                    }
                },
                left = {
                    _event.value = Event(UpcomingViewModelEvent.Error(it.message.orEmpty()))
                }
            )
        }
    }
}

data class UpcomingUiState(
    val loading: Boolean = false,
    val upcomingMovies: List<MovieModel>? = null
)

sealed class UpcomingViewModelEvent {
    object Idle : UpcomingViewModelEvent()

    data class Error(val message: String) : UpcomingViewModelEvent()
}