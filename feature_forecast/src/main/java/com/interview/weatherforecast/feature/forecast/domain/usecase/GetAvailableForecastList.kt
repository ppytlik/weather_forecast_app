package com.interview.weatherforecast.feature.forecast.domain.usecase

import com.interview.weatherforecast.core.forecast.model.ForecastLocation
import com.interview.weatherforecast.core.forecast.repository.ForecastRepository
import com.interview.weatherforecast.core_navigation.args.ForecastDetailsScreenArgs
import com.interview.weatherforecast.feature.forecast.domain.converter.DateConverter
import java.util.Locale

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
                val displayForecastText = dateConverter.formatDate(it.instant)
                val displayDate = dateConverter.formatFullDate(it.instant)
                AvailableForecast(
                    displayForecastText = displayForecastText,
                    displayDate = displayDate,
                    forecastDetailsScreenArgs = it.toForecastDetailsScreenArgs(dateConverter, displayForecastText)

                )
            }.let { items ->
                AvailableForecastListResult.Success(data = items)
            }
        }
    }
}

data class AvailableForecast(
    val displayForecastText: String,
    val displayDate: String,
    val forecastDetailsScreenArgs: ForecastDetailsScreenArgs?
)

sealed interface AvailableForecastListResult {
    data class Success(val data: List<AvailableForecast>) : AvailableForecastListResult
    data class Error(val message: String) : AvailableForecastListResult
}

fun ForecastRepository.ForecastData.toForecastDetailsScreenArgs(dateConverter: DateConverter, displayForecastText: String): ForecastDetailsScreenArgs {
    val locale = Locale.getDefault()
    return ForecastDetailsScreenArgs(
        title = displayForecastText,
        temperatureHigh = temperatureHigh,
        temperatureLow = temperatureLow,
        weatherCondition = weatherCondition,
        humidity = humidity,
        rainProbability = rainProbability,
        uvIndex = uvIndex,
        windSpeed = windSpeed,
        visibility = visibility,
        pressure = pressure,
        sunrise = dateConverter.convertTimeToLocale(sunrise, locale),
        sunset = dateConverter.convertTimeToLocale(sunset, locale)
    )
}
