package com.kursx.demo.analytics

interface AnalyticsConsumer {

    fun logScreen(screen: AnalyticsScreen)

    fun logEvent(event: AnalyticsEvent)
}
