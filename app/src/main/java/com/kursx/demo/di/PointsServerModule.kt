package com.kursx.demo.di

import com.kursx.chart.datasource.implementation.PointsServer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object PointsServerModule {

    @Provides
    fun provideApi(): PointsServer = Retrofit.Builder()
        .baseUrl("https://hr-challenge.interactivestandard.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(PointsServer::class.java)
}
