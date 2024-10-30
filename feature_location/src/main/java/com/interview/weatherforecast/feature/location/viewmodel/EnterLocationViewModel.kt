package com.interview.weatherforecast.feature.location.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.weatherforecast.core.location.service.LocationServiceManager
import com.interview.weatherforecast.core_navigation.args.ForecastListScreenArgs
import com.interview.weatherforecast.feature.location.ModuleNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EnterLocationViewModel(
        private val moduleNavigator: ModuleNavigator,
        private val locationServiceManager: LocationServiceManager
) : ViewModel() {
    fun onDoneClick(context: Context, locationName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val latLong = locationServiceManager.getLocationFromCityName(context, locationName)
            withContext(Dispatchers.Main) {
                moduleNavigator.openForecastScreen(
                        ForecastListScreenArgs(
                                locationName = locationName,
                                latitude = latLong?.lat,
                                longitude = latLong?.long
                        )
                )
            }
        }
    }
}
