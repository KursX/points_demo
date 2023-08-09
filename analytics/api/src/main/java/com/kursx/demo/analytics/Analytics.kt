package com.kursx.demo.analytics

interface Analytics {

    fun logScreen(screen: AnalyticsScreen, vararg services: AnalyticsService)

    fun logEvent(event: AnalyticsEvent, vararg services: AnalyticsService)
}
