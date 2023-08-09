package com.kursx.demo

import com.kursx.demo.analytics.Analytics
import com.kursx.demo.analytics.AnalyticsScreen
import com.kursx.demo.analytics.AnalyticsService
import com.kursx.demo.common.presentation.BaseViewModel
import com.kursx.demo.common.presentation.UiEffect
import com.kursx.demo.common.presentation.UiEvent
import com.kursx.demo.common.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val analytics: Analytics,
) : BaseViewModel<MenuViewModel.Event, UiState, UiEffect>() {

    override fun createInitialState() = object : UiState {}

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.LogScreenOpened -> logScreenOpened()
        }
    }

    private fun logScreenOpened() {
        analytics.logScreen(
            AnalyticsScreen("menu"),
            AnalyticsService.Logcat,
            AnalyticsService.AppsFlyer,
        )
    }

    sealed class Event : UiEvent {
        object LogScreenOpened : Event()
    }
}
