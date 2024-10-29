package com.interview.weatherforecast.navigation

import androidx.navigation.NavController
import com.interview.weatherforecast.core_navigation.destination.Destination
import com.interview.weatherforecast.core_navigation.navigation.AppNavigator

class AppNavigatorImpl : AppNavigator {

    private lateinit var navController: NavController

    override fun openScreen(destination: Destination) {
        navController.navigate(destination.route)
    }

    override fun back() {
        navController.popBackStack()
    }

    override fun setController(navController: NavController) {
        this.navController = navController
    }
}
