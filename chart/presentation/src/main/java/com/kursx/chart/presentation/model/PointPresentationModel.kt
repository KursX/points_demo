package com.kursx.chart.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class PointPresentationModel(
    val x: Float,
    val y: Float,
) : Parcelable
