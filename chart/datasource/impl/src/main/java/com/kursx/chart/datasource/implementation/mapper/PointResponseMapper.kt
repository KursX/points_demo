package com.kursx.chart.datasource.implementation.mapper

import com.kursx.chart.datasource.api.model.PointDataModel
import com.kursx.chart.datasource.api.model.PointsResponse

internal object PointResponseMapper : (PointsResponse.Point) -> PointDataModel {

    override fun invoke(point: PointsResponse.Point) = PointDataModel(
        x = point.x,
        y = point.y,
    )
}
