package com.kursx.chart.presentation.mapper

import com.kursx.chart.domain.model.PointDomainModel
import com.kursx.chart.presentation.model.PointPresentationModel

internal object PointDomainPresentationMapper : (PointDomainModel) -> PointPresentationModel {

    override fun invoke(point: PointDomainModel) = PointPresentationModel(
        x = point.x,
        y = point.y,
    )
}
