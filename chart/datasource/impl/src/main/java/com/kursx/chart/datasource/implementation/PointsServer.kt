package com.kursx.chart.datasource.implementation

import com.kursx.chart.datasource.api.model.PointsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PointsServer {

    @GET("/api/test/points")
    suspend fun receive(
        @Query("count") count: Int,
    ): PointsResponse
}
