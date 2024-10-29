package com.interview.weatherforecast.core_navigation.args

import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastListScreenArgs(
        private val locationName: String? = null,
        private val latitude: Float? = null,
        private val longitude: Float? = null
) : ScreenArgs
