package com.kursx.chart.datasource.api.model

import com.google.gson.annotations.SerializedName

class PointsResponse(
    @SerializedName("points") val points: List<Point>
) {

    class Point(
        @SerializedName("x") val x: Float,
        @SerializedName("y") val y: Float,
    )
}
