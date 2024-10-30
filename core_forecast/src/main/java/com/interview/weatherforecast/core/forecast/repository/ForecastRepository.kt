package com.interview.weatherforecast.core.forecast.repository

import com.interview.weatherforecast.core.forecast.model.ForecastLocation
import java.time.Instant

interface ForecastRepository {

    sealed interface ForecastResult {
        data class Success(val data: List<ForecastData>):ForecastResult
        data class Error(val message: String):ForecastResult
    }

    data class ForecastData(
        val instant: Instant,
        val temperatureHigh: Double,
        val temperatureLow: Double,
        val weatherCondition: String,
        val humidity: Double,
        val rainProbability: Double,
        val uvIndex: Int?,
        val windSpeed: Double,
        val visibility: Double,
        val pressure: Double,
        val sunrise: Instant?,
        val sunset: Instant?)

    suspend fun getWeatherForecastForLocation(location: ForecastLocation): ForecastResult
}