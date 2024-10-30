package com.interview.weatherforecast.core.forecast.repository

import android.content.Context
import android.util.Log
import com.interview.weatherforecast.core.forecast.model.ForecastLocation
import com.interview.weatherforecast.core.forecast.repository.DataMapper.asString
import com.interview.weatherforecast.forecastcore.BuildConfig
import com.telekommms.library.weathersdk.WeatherClient
import com.telekommms.library.weathersdk.models.Response
import com.telekommms.library.weathersdk.models.data.DataValuesDaily
import com.telekommms.library.weathersdk.models.data.TimelineItem
import com.telekommms.library.weathersdk.persistence.PlatformFileStore
import com.telekommms.library.weathersdk.persistence.PlatformSettingStore
import kotlinx.coroutines.CancellationException
import kotlinx.datetime.toJavaInstant

internal class WeatherSDKWrapper(androidContext: Context) : ForecastRepository {

    companion object {
        private const val TAG = "WeatherSDKWrapper"
    }

    private val weatherClient = WeatherClient(
        appId = BuildConfig.WEATHER_API_KEY,
        platformFileStore = PlatformFileStore(androidContext),
        platformSettingStore = PlatformSettingStore(androidContext)
    )

    override suspend fun getWeatherForecastForLocation(location: ForecastLocation) = try {
        val response = weatherClient.requestWeatherForecast(
            latitude = location.latitude,
            longitude = location.longitude,
        )
        when (response) {
            is Response.Failure -> ForecastRepository.ForecastResult.Error(response.reason.orEmpty())
            is Response.FromCache -> ForecastRepository.ForecastResult.Success(response.data.timelines.daily.toForecastData())
            is Response.Success -> ForecastRepository.ForecastResult.Success(response.data.timelines.daily.toForecastData())
        }
    } catch (error: Throwable) {
        Log.e(TAG, "Failed to request a weather forecast.")
        when (error) {
            is CancellationException -> throw error
            else -> ForecastRepository.ForecastResult.Error("Error")
        }
    }
}

private fun List<TimelineItem<DataValuesDaily>>.toForecastData(): List<ForecastRepository.ForecastData> {
   return this.map {
        ForecastRepository.ForecastData(
            instant = it.time.toJavaInstant(),
            temperatureHigh = it.values.temperatureMax,
            temperatureLow = it.values.temperatureMin,
            weatherCondition = it.values.weatherMin?.asString().orEmpty(),
            humidity = it.values.humidityAvg,
            rainProbability = it.values.precipitationProbabilityAvg,
            uvIndex = it.values.uvIndexMax,
            windSpeed = it.values.windSpeedMax,
            visibility = it.values.visibilityAvg,
            pressure = it.values.pressureSurfaceLevelAvg,
            sunrise = it.values.sunriseTime?.toJavaInstant(),
            sunset = it.values.sunriseTime?.toJavaInstant(),
        )
    }
}
