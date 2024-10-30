package com.interview.weatherforecast.core_navigation.args

import kotlinx.serialization.Serializable

@Serializable
data class ForecastListScreenArgs(
        val locationName: String? = null,
        val latitude: Float? = null,
        val longitude: Float? = null
)
