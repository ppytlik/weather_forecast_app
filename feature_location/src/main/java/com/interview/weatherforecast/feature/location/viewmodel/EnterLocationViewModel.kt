package com.interview.weatherforecast.feature.location.viewmodel

import androidx.lifecycle.ViewModel
import com.interview.weatherforecast.core_navigation.args.ForecastListScreenArgs
import com.interview.weatherforecast.feature.location.ModuleNavigator

class EnterLocationViewModel(
        private val moduleNavigator: ModuleNavigator
) : ViewModel() {
    fun onDoneClick(locationName: String) = moduleNavigator.openForecastScreen(
            ForecastListScreenArgs(locationName = locationName)
    )
}
