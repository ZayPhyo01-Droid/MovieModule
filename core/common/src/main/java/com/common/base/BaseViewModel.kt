package com.common.base

import androidx.lifecycle.ViewModel
import com.common.event.Event

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<S, T>(
    initialEvent: T,
    initialState: S,
) : ViewModel() {

    protected val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()


    val _event: MutableStateFlow<Event<T>> = MutableStateFlow(Event(initialEvent))
    val event: StateFlow<Event<T>> = _event.asStateFlow()

}
