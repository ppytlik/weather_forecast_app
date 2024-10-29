package com.interview.weatherforecast.feature.forecast.viewmodel

import androidx.lifecycle.ViewModel
import com.interview.weatherforecast.core_navigation.args.ForecastDetailsScreenArgs
import com.interview.weatherforecast.feature.forecast.ModuleNavigator

class ForecastListViewModel(
        val moduleNavigator: ModuleNavigator
) : ViewModel() {

    fun onForecastItemClick() = moduleNavigator.openForecastDetailsScreen(
            ForecastDetailsScreenArgs(

            )
    )
}