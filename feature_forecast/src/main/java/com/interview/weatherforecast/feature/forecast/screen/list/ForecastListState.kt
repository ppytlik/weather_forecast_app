package com.interview.weatherforecast.feature.forecast.screen.list

import com.interview.weatherforecast.feature.forecast.domain.usecase.AvailableForecast

data class ForecastListState(
    val locationName: String,
    val items: List<AvailableForecast> = emptyList(),
    val errorText: String? = null
)
