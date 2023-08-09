package com.kursx.chart.ui.compose

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.kursx.demo.common.ui.R

@Composable
fun TopBar(
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(onClick = onClickBack) {
                Icon(painterResource(id = R.drawable.ic_arrow_back), null)
            }
        },
        modifier = modifier,
    )
}
