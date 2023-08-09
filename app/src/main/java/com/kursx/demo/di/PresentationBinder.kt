package com.kursx.demo.di

import com.kursx.chart.presentation.ExportManager
import com.kursx.chart.presentation.ExportManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PresentationBinder {

    @Binds
    fun bindExportManager(impl: ExportManagerImpl): ExportManager
}
