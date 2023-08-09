package com.kursx.chart.ui.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kursx.chart.presentation.model.PointPresentationModel

@Composable
fun Table(
    points: List<PointPresentationModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .border(2.dp, Color.Black),
    ) {
        Row {
            TableCell(text = "x")
            TableCell(text = "y")
        }
        points.forEach { point ->
            Row {
                TableCell(text = point.x.toString())
                TableCell(text = point.y.toString())
            }
        }
    }
}

@Composable
private fun RowScope.TableCell(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier
            .border(1.dp, Color.Black)
            .weight(0.5f)
            .padding(8.dp),
    )
}
