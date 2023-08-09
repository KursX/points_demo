package com.kursx.chart.presentation.event

import com.kursx.demo.analytics.AnalyticsEvent

class SubmitClickEvent(count: Int) : AnalyticsEvent("submit_click", mapOf("count" to count))
