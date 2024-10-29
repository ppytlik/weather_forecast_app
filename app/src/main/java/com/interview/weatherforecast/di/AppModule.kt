package com.interview.weatherforecast.di

import com.interview.weatherforecast.core_navigation.navigation.AppNavigator
import com.interview.weatherforecast.navigation.AppNavigatorImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf<AppNavigator>(::AppNavigatorImpl)
}
