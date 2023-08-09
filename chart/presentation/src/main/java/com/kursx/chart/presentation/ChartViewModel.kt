package com.kursx.chart.presentation

import android.os.Parcelable
import android.view.View
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.kursx.chart.domain.usecase.ReceivePointsUseCase
import com.kursx.chart.presentation.ChartViewModelContract.Effect
import com.kursx.chart.presentation.ChartViewModelContract.Event
import com.kursx.chart.presentation.ChartViewModelContract.State
import com.kursx.chart.presentation.event.ChangeSmoothEvent
import com.kursx.chart.presentation.event.ExportChartEvent
import com.kursx.chart.presentation.event.HttpErrorEvent
import com.kursx.chart.presentation.event.SubmitClickEvent
import com.kursx.chart.presentation.event.UnknownPointsReceivingErrorEvent
import com.kursx.chart.presentation.mapper.PointDomainPresentationMapper
import com.kursx.chart.presentation.model.RectPresentationModel
import com.kursx.chart.presentation.model.TextExceptionPresentationModel
import com.kursx.demo.analytics.Analytics
import com.kursx.demo.analytics.AnalyticsScreen
import com.kursx.demo.analytics.AnalyticsService
import com.kursx.demo.common.domain.HttpTextException
import com.kursx.demo.common.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val receiveData: ReceivePointsUseCase,
    private val analytics: Analytics,
    private val exportManager: ExportManager,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<Event, State, Effect>() {

    private var lastInput: String? by savedStateHandle.delegate()

    override fun createInitialState(): State =
        savedStateHandle[SAVED_STATE] ?: State.Input(lastInput ?: "")

    init {
        viewModelScope.launch {
            uiState
                .filter { it is Parcelable }
                .collect { state ->
                    savedStateHandle[SAVED_STATE] = state
                }
        }
    }

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.OnSubmitClick -> {
                val count = lastInput?.toIntOrNull() ?: 0
                analytics.logEvent(
                    SubmitClickEvent(count),
                    AnalyticsService.Logcat,
                    AnalyticsService.Adjust,
                )
                submitCount(count)
            }

            is Event.OnBackClick -> onBackPressed()
            is Event.OnInputChanged -> saveInput(event.value)
            is Event.ChangeSmooth -> onChangeSmooth(event.smooth)
            is Event.LogScreen -> logScreen(event.screen)
            is Event.ExportChart -> exportChart(event.view, event.rect)
        }
    }

    private fun logScreen(screen: AnalyticsScreen) {
        analytics.logScreen(screen, AnalyticsService.Logcat, AnalyticsService.AppsFlyer)
    }

    private fun exportChart(
        view: View,
        rect: RectPresentationModel,
    ) {
        analytics.logEvent(ExportChartEvent, AnalyticsService.Logcat, AnalyticsService.Adjust)
        viewModelScope.launch {
            val bitmap = exportManager.createBitmap(view, rect)
            val uri = exportManager.saveBitmap(bitmap)

            setEffect { Effect.ExportChart(uri) }
        }
    }

    private fun onChangeSmooth(smooth: Boolean) {
        analytics.logEvent(
            ChangeSmoothEvent(smooth),
            AnalyticsService.Logcat,
            AnalyticsService.Adjust,
        )
        val currentState = uiState.value as State.Content
        setState {
            State.Content(
                points = currentState.points,
                smooth = smooth,
            )
        }
    }

    private fun onBackPressed() {
        setState {
            State.Input(lastInput ?: "")
        }
    }

    private fun saveInput(input: String) {
        lastInput = input
        setState {
            State.Input(lastInput ?: "")
        }
    }

    private fun submitCount(count: Int) {
        setState {
            State.Loading
        }
        viewModelScope.launch {
            receiveData(count)
                .onSuccess { points ->
                    setState {
                        State.Content(
                            points.map(PointDomainPresentationMapper::invoke),
                        )
                    }
                }
                .onFailure { e ->
                    e.printStackTrace()
                    setState {
                        if (e is HttpTextException) {
                            analytics.logEvent(
                                HttpErrorEvent,
                                AnalyticsService.Logcat,
                                AnalyticsService.Adjust,
                            )
                            State.Error(TextExceptionPresentationModel(e.text))
                        } else {
                            analytics.logEvent(
                                UnknownPointsReceivingErrorEvent,
                                AnalyticsService.Logcat,
                                AnalyticsService.Adjust,
                            )
                            State.Error(TextExceptionPresentationModel(e.stackTraceToString()))
                        }
                    }
                }
        }
    }

    companion object {

        private const val SAVED_STATE = "SAVED_STATE"
    }
}
