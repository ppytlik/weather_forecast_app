package com.interview.weatherforecast.feature.location

import com.interview.weatherforecast.core_navigation.navigation.AppNavigator
import com.interview.weatherforecast.core_navigation.args.ForecastListScreenArgs
import com.interview.weatherforecast.core_navigation.destination.Destination

class ModuleNavigator(
        private val appNavigator: AppNavigator
) {
    fun openForecastScreen(args: ForecastListScreenArgs) = appNavigator.openScreen(Destination.ForecastList(args))

    fun openAskForLocationScreen() = appNavigator.openScreen(Destination.AskForLocation)

    fun openEnterLocationScreen() = appNavigator.openScreen(Destination.EnterLocation)
}
