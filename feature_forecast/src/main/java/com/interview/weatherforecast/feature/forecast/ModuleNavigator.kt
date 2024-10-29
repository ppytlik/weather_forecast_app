package com.interview.weatherforecast.feature.forecast

import com.interview.weatherforecast.core_navigation.navigation.AppNavigator

internal class ModuleNavigator(
        private val appNavigator: AppNavigator
) {
    fun openForecastDetailsScreen() = Unit

    fun back() = appNavigator.back()
}
