package com.interview.weatherforecast

import android.app.Application
import com.interview.weatherforecast.di.appModule
import com.interview.weatherforecast.feature.location.featureLocationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(
                    listOf(appModule, featureLocationModule)
            )
        }
    }
}
