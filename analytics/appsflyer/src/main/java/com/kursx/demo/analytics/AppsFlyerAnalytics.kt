package com.kursx.demo.analytics

import android.content.Context
import com.appsflyer.AppsFlyerLib
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppsFlyerAnalytics @Inject constructor(
    @ApplicationContext private val context: Context,
) : AnalyticsConsumer {

    override fun logScreen(screen: AnalyticsScreen) {
        AppsFlyerLib.getInstance().logEvent(
            context,
            screen.eventName,
            screen.eventProperties,
        )
    }

    override fun logEvent(event: AnalyticsEvent) {
        AppsFlyerLib.getInstance().logEvent(
            context,
            event.eventName,
            event.eventProperties,
        )
    }
}
