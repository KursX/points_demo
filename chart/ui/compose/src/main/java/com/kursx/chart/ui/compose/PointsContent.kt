package com.kursx.chart.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kursx.chart.presentation.model.PointPresentationModel
import com.kursx.chart.presentation.model.RectPresentationModel
import com.kursx.demo.common.ui.R

@Composable
fun PointsContent(
    smooth: Boolean,
    points: List<PointPresentationModel>,
    onSmoothChanged: (Boolean) -> Unit,
    onClickBack: () -> Unit,
    onClickSave: (RectPresentationModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    var topBarBounds by remember { mutableStateOf<Rect?>(null) }
    Scaffold(
        topBar = {
            TopBar(
                onClickBack = onClickBack,
                modifier = Modifier
                    .onGloballyPositioned { topBarBounds = it.boundsInWindow() },
            )
        },
        modifier = modifier,
    ) { innerPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            val density = LocalDensity.current
            var graphBounds by remember { mutableStateOf<Rect?>(null) }

            Table(
                points = points,
            )

            ChartContent(
                smooth = smooth,
                points = points,
                onSmoothChanged = onSmoothChanged,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .onGloballyPositioned { graphBounds = it.boundsInWindow() },
            )

            if (graphBounds != null) {
                Button(
                    onClick = {
                        val elevation =
                            with(density) { AppBarDefaults.TopAppBarElevation.toPx() }
                        val topBarHeight = requireNotNull(topBarBounds).height - elevation
                        val rect = with(requireNotNull(graphBounds)) {
                            copy(
                                top = top - topBarHeight,
                                bottom = bottom - topBarHeight,
                            )
                        }
                        onClickSave(
                            RectPresentationModel(
                                left = rect.left,
                                top = rect.top,
                                right = rect.right,
                                bottom = rect.bottom,
                            ),
                        )
                    },
                ) {
                    Text(stringResource(R.string.save_graph))
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PointsContent(
        false,
        listOf(
            PointPresentationModel(1.1f, 1.1f),
            PointPresentationModel(2.1f, 2.0f),
            PointPresentationModel(3.1f, 3.0f),
        ),
        {},
        {},
        {},
    )
}
