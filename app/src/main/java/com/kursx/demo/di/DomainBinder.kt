package com.kursx.demo.di

import com.kursx.chart.domain.usecase.ReceivePointsUseCase
import com.kursx.chart.domain.usecase.ReceivePointsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainBinder {

    @Binds
    fun bindReceiveDataUseCase(impl: ReceivePointsUseCaseImpl): ReceivePointsUseCase
}
