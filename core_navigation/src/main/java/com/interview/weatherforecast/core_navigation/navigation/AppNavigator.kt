package com.interview.weatherforecast.core_navigation.navigation

import com.interview.weatherforecast.core_navigation.navigation.args.ForecastScreenArgs

interface AppNavigator {
    fun openForecastScreen(args: ForecastScreenArgs)

    fun back()
}
