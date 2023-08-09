package com.kursx.demo.analytics

import javax.inject.Inject

class MixpanelAnalytics @Inject constructor() : AnalyticsConsumer {

    override fun logScreen(screen: AnalyticsScreen) {
    }

    override fun logEvent(event: AnalyticsEvent) {
    }
}
