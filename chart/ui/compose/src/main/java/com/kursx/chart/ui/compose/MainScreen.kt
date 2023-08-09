package com.kursx.chart.ui.compose

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kursx.chart.presentation.ChartViewModel
import com.kursx.chart.presentation.ChartViewModelContract
import com.kursx.chart.presentation.ChartViewModelContract.Event
import com.kursx.chart.presentation.ChartViewModelContract.State
import com.kursx.chart.ui.compose.event.ChartScreenEvent
import com.kursx.chart.ui.compose.event.InputScreenEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun MainScreen(
    viewModel: ChartViewModel,
    openChooser: (Uri) -> Unit,
    onClickExit: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val view = LocalView.current

    LaunchedEffect(viewModel) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                is ChartViewModelContract.Effect.ExportChart -> openChooser(effect.uri)
            }
        }.launchIn(this)
    }

    val onClickBack = {
        viewModel.sendEvent(Event.OnBackClick)
    }
    BackHandler(onBack = onClickBack)

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState.value) {
        is State.Content -> {
            LaunchedEffect(Unit) {
                viewModel.sendEvent(Event.LogScreen(ChartScreenEvent))
            }
            PointsContent(
                smooth = state.smooth,
                points = state.points,
                onSmoothChanged = { checked ->
                    viewModel.sendEvent(Event.ChangeSmooth(checked))
                },
                onClickBack = onClickBack,
                onClickSave = { rect ->
                    viewModel.sendEvent(Event.ExportChart(rect, view))
                },
                modifier = modifier,
            )
        }

        is State.Error -> {
            Error(
                error = state.error,
                onClickBack = onClickBack,
                modifier = modifier,
            )
        }

        is State.Input -> {
            LaunchedEffect(Unit) {
                viewModel.sendEvent(Event.LogScreen(InputScreenEvent))
            }
            Input(
                value = state.value,
                onValueChanged = { value ->
                    viewModel.sendEvent(Event.OnInputChanged(value))
                },
                onClickBack = onClickExit,
                onClickSubmit = {
                    viewModel.sendEvent(Event.OnSubmitClick)
                },
                modifier = modifier,
            )
        }

        is State.Loading -> {
            Loading(modifier = modifier)
        }
    }
}
