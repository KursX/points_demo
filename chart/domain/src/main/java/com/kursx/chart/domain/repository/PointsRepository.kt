package com.kursx.chart.domain.repository

import com.kursx.chart.domain.model.PointDomainModel

interface PointsRepository {

    suspend fun receive(count: Int): List<PointDomainModel>
}
