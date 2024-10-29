package com.interview.weatherforecast.navigation

import android.os.Bundle
import androidx.navigation.NavController
import com.interview.weatherforecast.core_navigation.args.ForecastDetailsScreenArgs
import com.interview.weatherforecast.core_navigation.args.ForecastListScreenArgs
import com.interview.weatherforecast.core_navigation.args.ScreenArgs
import com.interview.weatherforecast.core_navigation.destination.Destination
import com.interview.weatherforecast.core_navigation.navigation.AppNavigator

class AppNavigatorImpl : AppNavigator {

    private var navController: NavController? = null

    override fun openScreen(destination: Destination) {
        navController?.navigate(destination.route)
    }

    override fun openScreen(destination: Destination, screenArgs: ScreenArgs) {
        navController?.navigate(destination.createRoute(screenArgs))
    }

    override fun back() {
        navController?.popBackStack()
    }

    override fun setController(navController: NavController) {
        this.navController = navController
    }

    override fun getArguments(): Bundle? {
        return navController?.currentBackStackEntry?.arguments
    }

    override fun clear() {
        navController = null
    }

    private fun Destination.createRoute(screenArgs: ScreenArgs) = when (this) {
        is Destination.ForecastList -> {
            "${route}/${screenArgs as ForecastListScreenArgs}"
        }
        is Destination.ForecastDetails -> {
            "${route}/${screenArgs as ForecastDetailsScreenArgs}"
        }
        else -> route
    }
}
