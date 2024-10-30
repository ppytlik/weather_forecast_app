package com.interview.weatherforecast.core_navigation.args

import kotlinx.serialization.Serializable

@Serializable
data class ForecastDetailsScreenArgs(
        val title: String,
        val temperatureHigh: Double?,
        val temperatureLow: Double?,
        val weatherCondition: String?,
        val humidity: Double?,
        val rainProbability: Double?,
        val uvIndex: Int?,
        val windSpeed: Double?,
        val visibility: Double?,
        val pressure: Double?,
        val sunrise: String,
        val sunset: String
)
