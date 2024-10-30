package com.interview.weatherforecast.di

import com.interview.weatherforecast.feature.forecast.featureForecastModule
import com.interview.weatherforecast.feature.location.featureLocationModule

internal object KoinInitializer {
    val modules = listOf(
        appModule, featureLocationModule
    ).plus(featureForecastModule)
}
