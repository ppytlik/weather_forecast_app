package com.interview.weatherforecast

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.interview.weatherforecast.core_navigation.args.ForecastDetailsScreenArgs
import com.interview.weatherforecast.core_navigation.args.ForecastListScreenArgs
import com.interview.weatherforecast.core_navigation.destination.Destination
import com.interview.weatherforecast.core_navigation.navigation.AppNavigator
import com.interview.weatherforecast.core_navigation.navigation.CustomNavType
import com.interview.weatherforecast.feature.forecast.screen.details.ForecastDetailsScreen
import com.interview.weatherforecast.feature.forecast.screen.list.ForecastListScreen
import com.interview.weatherforecast.feature.location.screen.AskForLocationScreen
import com.interview.weatherforecast.feature.location.screen.EnterLocationScreen
import com.interview.weatherforecast.feature.location.screen.WelcomeScreen
import org.koin.core.context.GlobalContext
import kotlin.reflect.typeOf

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
                    startDestination = Destination.Welcome
                ) {
                    composable<Destination.Welcome> {
                        WelcomeScreen().Content()
                    }
                    composable<Destination.AskForLocation> {
                        AskForLocationScreen().Content()
                    }
                    composable<Destination.EnterLocation> {
                        EnterLocationScreen().Content()
                    }
                    composable<Destination.ForecastList>(
                        typeMap = mapOf(
                            typeOf<ForecastListScreenArgs>() to CustomNavType.ForecastListArgs
                        )
                    ) {
                        val args = runCatching { it.toRoute<Destination.ForecastList>().args }.getOrNull()
                        args?.let { ForecastListScreen(args).Content() }
                    }
                    composable<Destination.ForecastDetails>(
                        typeMap = mapOf(
                            typeOf<ForecastDetailsScreenArgs>() to CustomNavType.ForecastDetailsArgs
                        )
                    ) {
                        val args = runCatching { it.toRoute<Destination.ForecastDetails>().args }.getOrNull()
                        args?.let { ForecastDetailsScreen(args).Content() }
                    }
                }
            }
        }
}
