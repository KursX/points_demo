package com.kursx.chart.presentation.model

class RectPresentationModel(
    val left: Float,
    val top: Float,
    val right: Float,
    val bottom: Float,
) {

    val width: Float
        get() = right - left

    val height: Float
        get() = bottom - top
}
