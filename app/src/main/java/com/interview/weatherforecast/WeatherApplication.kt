package com.interview.weatherforecast

import android.app.Application
import com.interview.weatherforecast.di.KoinInitializer
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(KoinInitializer.modules)
        }
    }
}
