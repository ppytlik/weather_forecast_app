package com.interview.weatherforecast.core.location

import com.interview.weatherforecast.core.location.permission.LocationPermissionManager
import com.interview.weatherforecast.core.location.service.LocationServiceManager
import org.koin.dsl.module

val coreLocationModule = module {
    single { LocationPermissionManager() }
    single { LocationServiceManager() }
}
