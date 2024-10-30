package com.interview.weatherforecast.core.forecast.repository

import android.content.Context
import android.util.Log
import com.interview.weatherforecast.core.forecast.model.ForecastLocation
import com.interview.weatherforecast.forecastcore.BuildConfig
import com.telekommms.library.weathersdk.WeatherClient
import com.telekommms.library.weathersdk.models.Response
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
            longitude = location.longitude
        )
        Log.d("TESTOWANIE", response.toString())
        when (response) {
            is Response.Failure -> ForecastRepository.ForecastResult.Error(response.reason.orEmpty())
            is Response.FromCache -> {
                val daily = response.data.timelines.daily.map {
                    it.values.weatherMax
                    it.time.toJavaInstant()
                }
                ForecastRepository.ForecastResult.Success(daily)
            }

            is Response.Success -> {
                val daily = response.data.timelines.daily.map {
                    it.time.toJavaInstant()
                }
                ForecastRepository.ForecastResult.Success(daily)
            }
        }
    } catch (error: Throwable) {
        Log.e(TAG, "Failed to request a weather forecast.")
        when (error) {
            is CancellationException -> throw error
            else -> ForecastRepository.ForecastResult.Error("Error")
        }
    }
}