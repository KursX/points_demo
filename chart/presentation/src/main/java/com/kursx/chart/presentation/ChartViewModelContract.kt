package com.kursx.chart.presentation

import android.net.Uri
import android.os.Parcelable
import android.view.View
import com.kursx.chart.presentation.model.PointPresentationModel
import com.kursx.chart.presentation.model.RectPresentationModel
import com.kursx.chart.presentation.model.TextExceptionPresentationModel
import com.kursx.demo.analytics.AnalyticsScreen
import com.kursx.demo.common.presentation.UiEffect
import com.kursx.demo.common.presentation.UiEvent
import com.kursx.demo.common.presentation.UiState
import kotlinx.parcelize.Parcelize

object ChartViewModelContract {

    sealed class Event : UiEvent {

        object OnSubmitClick : Event()

        class ExportChart(
            val rect: RectPresentationModel,
            val view: View,
        ) : Event()

        class LogScreen(val screen: AnalyticsScreen) : Event()

        class ChangeSmooth(val smooth: Boolean) : Event()

        class OnInputChanged(val value: String) : Event()

        object OnBackClick : Event()
    }

    sealed class State : UiState {

        @Parcelize
        class Input(val value: String) : State(), Parcelable

        @Parcelize
        class Content(
            val points: List<PointPresentationModel>,
            val smooth: Boolean = false,
        ) : State(), Parcelable

        object Loading : State()

        class Error(val error: TextExceptionPresentationModel) : State()
    }

    sealed class Effect : UiEffect {

        class ExportChart(val uri: Uri) : Effect()
    }
}
