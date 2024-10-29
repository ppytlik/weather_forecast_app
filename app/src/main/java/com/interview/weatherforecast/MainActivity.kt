package com.interview.weatherforecast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.interview.weatherforecast.forecastcore.WeatherSDKWrapper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isGoodWeather = WeatherSDKWrapper(this).isGoodWeather()

        println("Wheather good? $isGoodWeather")
    }
}
