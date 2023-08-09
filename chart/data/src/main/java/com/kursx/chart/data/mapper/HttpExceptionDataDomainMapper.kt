package com.kursx.chart.data.mapper

import com.kursx.chart.datasource.api.model.HttpExceptionDataModel
import com.kursx.demo.common.domain.HttpTextException

internal object HttpExceptionDataDomainMapper : (HttpExceptionDataModel) -> HttpTextException {

    override fun invoke(exception: HttpExceptionDataModel) =
        HttpTextException(e = exception.e, text = exception.text)
}
