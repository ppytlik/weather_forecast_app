package com.interview.weatherforecast.core_navigation.navigation

import androidx.navigation.NavController
import com.interview.weatherforecast.core_navigation.args.ScreenArgs
import com.interview.weatherforecast.core_navigation.destination.Destination

interface AppNavigator {

    fun openScreen(destination: Destination)

    fun openScreen(destination: Destination, screenArgs: ScreenArgs)

    fun back()

    fun setController(navController: NavController)

    fun clear()
}
