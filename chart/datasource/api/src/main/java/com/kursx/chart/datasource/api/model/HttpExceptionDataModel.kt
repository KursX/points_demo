package com.kursx.chart.datasource.api.model

class HttpExceptionDataModel(
    val e: Throwable,
    val text: String
) : Throwable(e)
