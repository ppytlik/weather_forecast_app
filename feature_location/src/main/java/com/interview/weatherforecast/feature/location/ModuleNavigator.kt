package com.interview.weatherforecast.feature.location

import com.interview.weatherforecast.core_navigation.navigation.AppNavigator
import com.interview.weatherforecast.core_navigation.navigation.args.ForecastScreenArgs

class ModuleNavigator(
        private val appNavigator: AppNavigator
) {
    fun openForecastScreen(args: ForecastScreenArgs) = appNavigator.openForecastScreen(args)

    fun openAskForLocationScreen() = Unit

    fun openEnterLocationScreen() = Unit
}
