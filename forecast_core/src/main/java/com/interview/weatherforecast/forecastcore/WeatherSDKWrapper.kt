package com.interview.weatherforecast.forecastcore

import com.telekommms.library.weathersdk.models.data.WeatherCode

class WeatherSDKWrapper {
    private var test: WeatherCode? = null

    fun isGoodWeather(): Boolean = when(test) {
        WeatherCode.CLEAR_DAY -> true
        else -> false
    }
}
