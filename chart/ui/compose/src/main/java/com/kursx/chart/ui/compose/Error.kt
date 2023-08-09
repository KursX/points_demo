package com.kursx.chart.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kursx.chart.presentation.model.TextExceptionPresentationModel

@Composable
fun Error(
    error: TextExceptionPresentationModel,
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopBar(
                onClickBack = onClickBack,
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Text(error.text)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    Error(NullPointerException())
}
