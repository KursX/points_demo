package com.kursx.chart.ui.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kursx.demo.common.ui.R
import kotlinx.coroutines.delay

@Composable
fun Input(
    value: String,
    onValueChanged: (String) -> Unit,
    onClickBack: () -> Unit,
    onClickSubmit: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BackHandler(onBack = onClickBack)
    val focusRequester = remember { FocusRequester() }
    Scaffold(
        topBar = {
            TopBar(
                onClickBack = onClickBack,
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Text(stringResource(R.string.info_block))
            OutlinedTextField(
                value = TextFieldValue(value, TextRange(value.length)),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = { onClickSubmit() },
                ),
                onValueChange = { value ->
                    onValueChanged(value.text.filter { it.isDigit() })
                },
                placeholder = {
                    Text(stringResource(R.string.placeholder))
                },
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .focusRequester(focusRequester),
            )
            if ((value.toIntOrNull() ?: 0) > 0) {
                Button(onClick = onClickSubmit) {
                    Text(text = stringResource(R.string.go))
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        delay(300)
        focusRequester.requestFocus()
    }
}

@Preview
@Composable
private fun Preview() {
    Input("", {}, {}, {})
}
