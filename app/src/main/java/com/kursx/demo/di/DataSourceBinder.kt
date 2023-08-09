package com.kursx.demo.di

import com.kursx.chart.datasource.api.NetworkDataSource
import com.kursx.chart.datasource.implementation.NetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceBinder {
    @Binds
    fun bindService(impl: NetworkDataSourceImpl): NetworkDataSource
}
