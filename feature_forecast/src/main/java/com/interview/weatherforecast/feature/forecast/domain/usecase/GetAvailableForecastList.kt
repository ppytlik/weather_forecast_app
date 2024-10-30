package com.interview.weatherforecast.feature.forecast.domain.usecase

import com.interview.weatherforecast.core.forecast.model.ForecastLocation
import com.interview.weatherforecast.core.forecast.repository.ForecastRepository
import com.interview.weatherforecast.core_navigation.args.ForecastDetailsScreenArgs
import com.interview.weatherforecast.feature.forecast.domain.converter.DateConverter
import java.util.Locale

class GetAvailableForecastList(
    private val forecastRepository: ForecastRepository,
    private val dateConverter: DateConverter,
) {
    suspend operator fun invoke(latitude: Float?, longitude: Float?): AvailableForecastListResult {
        return if (latitude != null && longitude != null) {
            when (val result = forecastRepository.getWeatherForecastForLocation(ForecastLocation(latitude.toDouble(), longitude.toDouble()))) {
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
        } else {
            AvailableForecastListResult.Error("Missing location")
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
