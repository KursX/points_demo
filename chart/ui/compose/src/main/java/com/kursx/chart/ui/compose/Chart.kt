package com.kursx.chart.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kursx.chart.presentation.model.PointPresentationModel
import com.kursx.demo.common.ui.R
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.style.currentChartStyle
import com.patrykandpatrick.vico.core.DefaultDimens
import com.patrykandpatrick.vico.core.chart.DefaultPointConnector
import com.patrykandpatrick.vico.core.component.marker.MarkerComponent
import com.patrykandpatrick.vico.core.component.shape.ShapeComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes.pillShape
import com.patrykandpatrick.vico.core.component.text.textComponent
import com.patrykandpatrick.vico.core.entry.entryModelOf

private val Marker = object : MarkerComponent(
    label = textComponent { textSizeSp = 0f },
    indicator = ShapeComponent(pillShape),
    guideline = null,
) {
    init {
        indicatorSizeDp = 8f
    }
}

@Composable
fun ChartContent(
    smooth: Boolean,
    points: List<PointPresentationModel>,
    onSmoothChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val data by remember(points) {
        mutableStateOf(points.map { it.x.toInt() to it.y.toInt() }.toTypedArray())
    }
    val markers by remember(points) {
        mutableStateOf(data.map { it.first.toFloat() }.associateWith { Marker })
    }

    Chart(
        chart = lineChart(
            lines = currentChartStyle.lineChart.lines.onEach { line ->
                line.pointConnector = DefaultPointConnector(
                    if (smooth) DefaultDimens.CUBIC_STRENGTH else 0f,
                )
            },
            persistentMarkers = markers,
        ),
        model = entryModelOf(*data),
        startAxis = startAxis(),
        bottomAxis = bottomAxis(),
        modifier = modifier,
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = smooth,
            onCheckedChange = onSmoothChanged,
        )
        Text(
            text = stringResource(R.string.smooth),
            modifier = Modifier.clickable { onSmoothChanged(!smooth) },
        )
    }
}
