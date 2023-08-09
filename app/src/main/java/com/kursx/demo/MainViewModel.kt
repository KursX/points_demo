package com.kursx.demo

import androidx.lifecycle.viewModelScope
import com.kursx.demo.analytics.Analytics
import com.kursx.demo.analytics.AnalyticsEvent
import com.kursx.demo.common.presentation.BaseViewModel
import com.kursx.demo.common.presentation.UiEffect
import com.kursx.demo.common.presentation.UiEvent
import com.kursx.demo.common.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val analytics: Analytics,
) : BaseViewModel<MainViewModel.Event, MainViewModel.State, UiEffect>() {

    init {
        viewModelScope.launch {
            delay(100)
            // ... here long running initialization
            sendEvent(Event.LogScreenOpened)
            setState { State.Initialized }
        }
    }

    override fun createInitialState() = State.Loading

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.LogScreenOpened -> logAppInitialized()
        }
    }

    private fun logAppInitialized() {
        analytics.logEvent(AnalyticsEvent("app_initialized"))
    }

    sealed class State : UiState {
        object Loading : State()
        object Initialized : State()
    }

    sealed class Event : UiEvent {
        object LogScreenOpened : Event()
    }
}
