package com.kursx.chart.presentation.event

import com.kursx.demo.analytics.AnalyticsEvent

class ChangeSmoothEvent(checked: Boolean) :
    AnalyticsEvent("change_smooth_click", mapOf("checked" to checked))
