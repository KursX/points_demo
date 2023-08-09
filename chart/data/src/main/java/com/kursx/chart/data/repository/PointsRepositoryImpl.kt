package com.kursx.chart.data.repository

import com.kursx.chart.data.mapper.HttpExceptionDataDomainMapper
import com.kursx.chart.data.mapper.PointDataDomainMapper
import com.kursx.chart.datasource.api.NetworkDataSource
import com.kursx.chart.datasource.api.model.HttpExceptionDataModel
import com.kursx.chart.domain.repository.PointsRepository
import javax.inject.Inject

class PointsRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
) : PointsRepository {

    override suspend fun receive(count: Int) = try {
        networkDataSource
            .receive(count)
            .map(PointDataDomainMapper::invoke)
            .sortedBy { it.x }
    } catch (e: HttpExceptionDataModel) {
        throw HttpExceptionDataDomainMapper(e)
    }
}
