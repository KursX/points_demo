package com.kursx.demo.analytics

import android.util.Log
import javax.inject.Inject

class LogcatAnalytics @Inject constructor() : AnalyticsConsumer {

    override fun logScreen(screen: AnalyticsScreen) {
        Log.i("Screen", screen.eventName)
    }

    override fun logEvent(event: AnalyticsEvent) {
        Log.i("Event", event.eventName)
        event.eventProperties.forEach { (eventKey, eventValue) ->
            Log.i("Event", "$eventKey: $eventValue")
        }
    }
}
