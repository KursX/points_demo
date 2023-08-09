package com.kursx.chart.datasource.api

import com.kursx.chart.datasource.api.model.PointDataModel

interface NetworkDataSource {

    suspend fun receive(count: Int): List<PointDataModel>
}
