package com.interview.weatherforecast.feature.forecast.screen.details

import androidx.lifecycle.ViewModel
import com.interview.weatherforecast.feature.forecast.ModuleNavigator

class ForecastDetailsViewModel(
    private val moduleNavigator: ModuleNavigator,
) : ViewModel() {
    fun onBackClicked() = moduleNavigator.back()
}