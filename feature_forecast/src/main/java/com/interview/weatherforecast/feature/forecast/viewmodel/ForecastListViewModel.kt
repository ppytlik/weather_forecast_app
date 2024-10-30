package com.interview.weatherforecast.feature.forecast.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.weatherforecast.core_navigation.args.ForecastDetailsScreenArgs
import com.interview.weatherforecast.core_navigation.args.ForecastListScreenArgs
import com.interview.weatherforecast.feature.forecast.ModuleNavigator
import com.interview.weatherforecast.feature.forecast.state.ForecastListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ForecastListViewModel(
        val moduleNavigator: ModuleNavigator
) : ViewModel() {

    private val stateFlow: MutableStateFlow<ForecastListState> = MutableStateFlow(ForecastListState(locationName = ""))

    var args: ForecastListScreenArgs? = null

    fun getScreenStateFlow(): StateFlow<ForecastListState> = stateFlow

    fun onLocationButtonClick() = moduleNavigator.back()

    fun onForecastItemClick() = moduleNavigator.openForecastDetailsScreen(ForecastDetailsScreenArgs())

    fun loadData(args: ForecastListScreenArgs) {
        this.args = args
        viewModelScope.launch(Dispatchers.IO) {
            args.locationName?.let { locationName ->
                stateFlow.update { stateFlow.value.copy(locationName = locationName) }
            }
        }
    }
}
