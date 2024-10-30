package com.interview.weatherforecast.core_navigation.args

import kotlinx.serialization.Serializable

@Serializable
data class ForecastDetailsScreenArgs(
        val title: String? = null,
        val highestValue: String? = null,
        val lowestValue: String? = null,
        val generalConditions: String? = null,
        val averageHumidity: Float? = null,
        val averageProbabilityOfRain: Float? = null,
        val maximumUVIndex: Int? = null,
        val maximumWindSpeed: Int? = null,
        val averageVisibility: Int? = null,
        val averageAirPressure: Int? = null,
        val locationName: String? = null,
        val sunriseTime: String? = null,
        val sunsetTime: String? = null
) : ScreenArgs
