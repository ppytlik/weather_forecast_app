package com.interview.weatherforecast.core_navigation.destination

sealed class Destination(val route: String) {
    data object Welcome : Destination("welcome")
    data object AskForLocation : Destination("ask_for_location")
    data object EnterLocation : Destination("enter_location")
    data object ForecastList : Destination("forecast_list")
    data object ForecastDetails : Destination("forecast_details")
}
