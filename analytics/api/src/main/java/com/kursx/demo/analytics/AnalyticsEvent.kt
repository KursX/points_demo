package com.kursx.demo.analytics

open class AnalyticsEvent(
    val eventName: String,
    open val eventProperties: Map<String, Any> = mapOf()
)
