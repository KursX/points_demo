package com.kursx.demo.di

import com.kursx.chart.data.repository.PointsRepositoryImpl
import com.kursx.chart.domain.repository.PointsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryBinder {

    @Binds
    fun bindRepository(impl: PointsRepositoryImpl): PointsRepository
}
