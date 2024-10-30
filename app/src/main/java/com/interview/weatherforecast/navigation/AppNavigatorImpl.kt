package com.interview.weatherforecast.navigation

import android.os.Bundle
import androidx.navigation.NavController
import com.interview.weatherforecast.core_navigation.destination.Destination
import com.interview.weatherforecast.core_navigation.navigation.AppNavigator

class AppNavigatorImpl : AppNavigator {

    private var navController: NavController? = null

    override fun openScreen(destination: Destination) {
        navController?.navigate(destination)
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
}
