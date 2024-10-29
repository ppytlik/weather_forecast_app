package com.interview.weatherforecast.feature.location

import com.interview.weatherforecast.feature.location.viewmodel.EnterLocationViewModel
import com.interview.weatherforecast.feature.location.viewmodel.WelcomeViewModel
import org.koin.core.module.dsl.singleOf

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureLocationModule = module {
    viewModelOf(::WelcomeViewModel)
    viewModelOf(::EnterLocationViewModel)
    singleOf(::ModuleNavigator)
}
