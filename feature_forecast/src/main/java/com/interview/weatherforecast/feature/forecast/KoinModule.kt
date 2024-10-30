package com.interview.weatherforecast.feature.forecast

import android.content.Context
import com.interview.weatherforecast.core.forecast.coreForecastKoinModule
import com.interview.weatherforecast.feature.forecast.domain.converter.DateConverter
import com.interview.weatherforecast.feature.forecast.domain.converter.TextLocator
import com.interview.weatherforecast.feature.forecast.domain.usecase.GetAvailableForecastList
import com.interview.weatherforecast.feature.forecast.screen.list.ForecastListViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureForecastModule = module {
    single<TextLocator> {
        object : TextLocator {
            override val todayText = "Today"
            override val tomorrowText = "Tomorrow"
        }
    }
    singleOf(::DateConverter)

    single { GetAvailableForecastList(forecastRepository = get(), dateConverter = get()) }
    singleOf(::ModuleNavigator)
    viewModelOf(::ForecastListViewModel)
}.plus(coreForecastKoinModule)