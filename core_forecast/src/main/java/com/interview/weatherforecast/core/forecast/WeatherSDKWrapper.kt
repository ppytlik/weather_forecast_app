package com.interview.weatherforecast.core.forecast

import android.content.Context
import android.util.Log
import com.interview.weatherforecast.forecastcore.BuildConfig

import com.telekommms.library.weathersdk.WeatherClient
import com.telekommms.library.weathersdk.persistence.PlatformFileStore
import com.telekommms.library.weathersdk.persistence.PlatformSettingStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "SDKWrapper"

class WeatherSDKWrapper(androidContext: Context) {
    init {
        val sdk = WeatherClient(
                appId = BuildConfig.WEATHER_API_KEY,
                platformFileStore = PlatformFileStore(androidContext),
                platformSettingStore = PlatformSettingStore(androidContext)
        )

        CoroutineScope(Dispatchers.IO).launch {
            runCatching {
                sdk.requestWeatherForecast(0.0,0.0)
            }.onFailure { 
                Log.e(TAG, "Failed to request a weather forecast.")
            }
        }
    }
}
