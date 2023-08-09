package com.kursx.demo.di

import com.kursx.demo.analytics.Analytics
import com.kursx.demo.analytics.AnalyticsConsumer
import com.kursx.demo.analytics.AnalyticsEvent
import com.kursx.demo.analytics.AnalyticsScreen
import com.kursx.demo.analytics.AnalyticsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {

    @Provides
    fun provideAnalytics(
        map: Map<AnalyticsService, @JvmSuppressWildcards AnalyticsConsumer>,
    ): Analytics {
        return object : Analytics {
            override fun logScreen(screen: AnalyticsScreen, vararg services: AnalyticsService) {
                services.ifEmpty {
                    AnalyticsService.values()
                }.forEach { service ->
                    map.getValue(service).logScreen(screen)
                }
            }

            override fun logEvent(event: AnalyticsEvent, vararg services: AnalyticsService) {
                services.ifEmpty {
                    AnalyticsService.values()
                }.forEach { service ->
                    map.getValue(service).logEvent(event)
                }
            }
        }
    }
}
