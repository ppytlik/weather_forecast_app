package com.interview.weatherforecast.feature.forecast.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.weatherforecast.core_navigation.args.ForecastListScreenArgs
import com.interview.weatherforecast.feature.forecast.ModuleNavigator
import com.interview.weatherforecast.feature.forecast.domain.usecase.AvailableForecast
import com.interview.weatherforecast.feature.forecast.domain.usecase.AvailableForecastListResult
import com.interview.weatherforecast.feature.forecast.domain.usecase.GetAvailableForecastList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ForecastListViewModel(
    private val moduleNavigator: ModuleNavigator,
    private val getAvailableForecastList: GetAvailableForecastList,
) : ViewModel() {

    private val stateFlow: MutableStateFlow<ForecastListState> = MutableStateFlow(ForecastListState(locationName = ""))

    var args: ForecastListScreenArgs? = null

    fun getScreenStateFlow(): StateFlow<ForecastListState> = stateFlow

    fun onLocationButtonClick() = moduleNavigator.back()

    fun onForecastItemClick(item:AvailableForecast) = item.forecastDetailsScreenArgs?.let { moduleNavigator.openForecastDetailsScreen(it) }

    fun loadData(args: ForecastListScreenArgs) {
        this.args = args
        viewModelScope.launch(Dispatchers.IO) {
            val locationName = args.locationName

            val (items, errorText) = when (val listResult = getAvailableForecastList()) {
                is AvailableForecastListResult.Success -> listResult.data to null
                is AvailableForecastListResult.Error -> emptyList<AvailableForecast>() to listResult.message
            }

            stateFlow.update {
                stateFlow.value.copy(locationName = locationName.orEmpty(), items = items, errorText = errorText)
            }
        }
    }
}
