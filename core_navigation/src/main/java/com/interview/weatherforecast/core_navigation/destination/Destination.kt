package com.interview.weatherforecast.core_navigation.destination

import com.interview.weatherforecast.core_navigation.args.ForecastListScreenArgs
import kotlinx.serialization.Serializable

@Serializable
sealed class Destination() {

    @Serializable
    data object Welcome : Destination()

    @Serializable
    data object AskForLocation : Destination()

    @Serializable
    data object EnterLocation : Destination()

    @Serializable
    data class ForecastList(val args: ForecastListScreenArgs) : Destination()

    @Serializable
    data object ForecastDetails : Destination()
}
