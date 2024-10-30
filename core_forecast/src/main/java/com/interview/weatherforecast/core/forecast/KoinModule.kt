package com.interview.weatherforecast.core.forecast

import com.interview.weatherforecast.core.forecast.repository.ForecastRepository
import com.interview.weatherforecast.core.forecast.repository.WeatherSDKWrapper
import org.koin.dsl.module

val coreForecastKoinModule = module {
    single<ForecastRepository> {
        WeatherSDKWrapper(get())
    }
}