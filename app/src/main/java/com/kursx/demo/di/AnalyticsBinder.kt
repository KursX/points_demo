package com.kursx.demo.di

import com.kursx.demo.analytics.AdjustAnalytics
import com.kursx.demo.analytics.AnalyticsConsumer
import com.kursx.demo.analytics.AnalyticsService
import com.kursx.demo.analytics.AppsFlyerAnalytics
import com.kursx.demo.analytics.LogcatAnalytics
import com.kursx.demo.analytics.MixpanelAnalytics
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface AnalyticsBinder {

    @Binds
    @IntoMap
    @AnalyticsKey(AnalyticsService.AppsFlyer)
    fun bindAppsFlyer(appsFlyer: AppsFlyerAnalytics): AnalyticsConsumer

    @Binds
    @IntoMap
    @AnalyticsKey(AnalyticsService.Mixpanel)
    fun bindMixpanel(appsFlyer: MixpanelAnalytics): AnalyticsConsumer

    @Binds
    @IntoMap
    @AnalyticsKey(AnalyticsService.Adjust)
    fun bindAdjust(appsFlyer: AdjustAnalytics): AnalyticsConsumer

    @Binds
    @IntoMap
    @AnalyticsKey(AnalyticsService.Logcat)
    fun bindLogcat(appsFlyer: LogcatAnalytics): AnalyticsConsumer
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class AnalyticsKey(val value: AnalyticsService)
