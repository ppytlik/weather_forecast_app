package com.interview.weatherforecast.core.forecast.repository

import com.interview.weatherforecast.core.forecast.model.ForecastLocation
import java.time.Instant

interface ForecastRepository {

    sealed interface ForecastResult {
        data class Success(val data: List<Instant>):ForecastResult
        data class Error(val message: String):ForecastResult
    }

    suspend fun getWeatherForecastForLocation(location: ForecastLocation): ForecastResult
}