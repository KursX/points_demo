package com.kursx.chart.data.mapper

import com.kursx.chart.datasource.api.model.PointDataModel
import com.kursx.chart.domain.model.PointDomainModel

internal object PointDataDomainMapper : (PointDataModel) -> PointDomainModel {

    override fun invoke(point: PointDataModel) =
        PointDomainModel(x = point.x, y = point.y)
}
