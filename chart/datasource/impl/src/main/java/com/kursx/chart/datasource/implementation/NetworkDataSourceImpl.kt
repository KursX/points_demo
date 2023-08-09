package com.kursx.chart.datasource.implementation

import com.kursx.chart.datasource.api.NetworkDataSource
import com.kursx.chart.datasource.api.model.HttpExceptionDataModel
import com.kursx.chart.datasource.api.model.PointDataModel
import com.kursx.chart.datasource.implementation.mapper.PointResponseMapper
import retrofit2.HttpException
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val pointsServer: PointsServer,
) : NetworkDataSource {

    override suspend fun receive(count: Int): List<PointDataModel> {
        return try {
            pointsServer.receive(count).points.map(PointResponseMapper::invoke)
        } catch (e: HttpException) {
            val message = e.response()?.errorBody()?.string()
            if (message != null) {
                throw HttpExceptionDataModel(e, message)
            } else {
                throw e
            }
        }
    }
}
