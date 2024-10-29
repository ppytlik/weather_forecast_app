package com.interview.weatherforecast.feature.forecast

import com.interview.weatherforecast.feature.forecast.viewmodel.ForecastListViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureForecastModule = module {
    viewModelOf(::ForecastListViewModel)
    singleOf(::ModuleNavigator)
}
