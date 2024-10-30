package com.interview.weatherforecast.feature.forecast

import com.interview.weatherforecast.core_navigation.args.ForecastDetailsScreenArgs
import com.interview.weatherforecast.core_navigation.destination.Destination
import com.interview.weatherforecast.core_navigation.navigation.AppNavigator

class ModuleNavigator(
        private val appNavigator: AppNavigator
) {
    fun openForecastDetailsScreen(args: ForecastDetailsScreenArgs) = appNavigator.openScreen(Destination.ForecastDetails)

    fun getNavigationArguments() = appNavigator.getArguments()

    fun back() = appNavigator.back()
}
