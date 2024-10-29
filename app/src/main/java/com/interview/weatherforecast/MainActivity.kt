package com.interview.weatherforecast

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.interview.weatherforecast.core_navigation.destination.Destination
import com.interview.weatherforecast.core_navigation.navigation.AppNavigator
import com.interview.weatherforecast.feature.forecast.screen.ForecastDetailsScreen
import com.interview.weatherforecast.feature.forecast.screen.ForecastListScreen
import com.interview.weatherforecast.feature.location.screen.AskForLocationScreen
import com.interview.weatherforecast.feature.location.screen.EnterLocationScreen
import com.interview.weatherforecast.feature.location.screen.WelcomeScreen
import org.koin.core.context.GlobalContext

class MainActivity : AppCompatActivity() {

    private val appNavigator: AppNavigator by lazy { GlobalContext.get().get<AppNavigator>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            DisposableEffect(key1 = navController) {
                appNavigator.setController(navController)
                onDispose { }
            }

            NavHost(
                    navController = navController,
                    startDestination = Destination.Welcome.route
            ) {
                composable(route = Destination.Welcome.route) {
                    WelcomeScreen().Content()
                }
                composable(route = Destination.AskForLocation.route) {
                    AskForLocationScreen().Content()
                }
                composable(route = Destination.EnterLocation.route) {
                    EnterLocationScreen().Content()
                }
                composable(route = Destination.ForecastList.route) {
                    ForecastListScreen().Content()
                }
                composable(route = Destination.ForecastDetails.route) {
                    ForecastDetailsScreen().Content()
                }
            }
        }
    }
}
