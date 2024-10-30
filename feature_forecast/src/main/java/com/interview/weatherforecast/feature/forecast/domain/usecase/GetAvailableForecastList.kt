package com.interview.weatherforecast.feature.forecast.domain.usecase

import com.interview.weatherforecast.core.forecast.model.ForecastLocation
import com.interview.weatherforecast.core.forecast.repository.ForecastRepository
import com.interview.weatherforecast.feature.forecast.domain.converter.DateConverter

class GetAvailableForecastList(
    private val forecastRepository: ForecastRepository,
    private val dateConverter: DateConverter,
    private val locationProvider: ForecastLocationProvider = ForecastLocationProvider,
) {

    fun interface ForecastLocationProvider {
        operator fun invoke(): ForecastLocation

        companion object : ForecastLocationProvider {
            override fun invoke() = ForecastLocation(53.42894, 14.55302)
        }
    }

    suspend operator fun invoke(): AvailableForecastListResult {
        return when (val result = forecastRepository.getWeatherForecastForLocation(locationProvider())) {
            is ForecastRepository.ForecastResult.Error -> AvailableForecastListResult.Error(result.message)
            is ForecastRepository.ForecastResult.Success -> result.data.map {
                AvailableForecast(
                    displayForecastText = dateConverter.formatDate(it),
                    displayDate = dateConverter.formatFullDate(it)
                )
            }.let { items ->
                AvailableForecastListResult.Success(data = items)
            }
        }
    }
}


data class AvailableForecast(val displayForecastText: String, val displayDate: String)

sealed interface AvailableForecastListResult {
    data class Success(val data: List<AvailableForecast>) : AvailableForecastListResult
    data class Error(val message: String) : AvailableForecastListResult
}