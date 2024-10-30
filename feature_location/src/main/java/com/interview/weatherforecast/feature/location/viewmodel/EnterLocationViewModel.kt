package com.interview.weatherforecast.feature.location.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.weatherforecast.core.location.service.LocationServiceManager
import com.interview.weatherforecast.core_navigation.args.ForecastListScreenArgs
import com.interview.weatherforecast.feature.location.ModuleNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "EnterLocationViewModel"

class EnterLocationViewModel(
        private val moduleNavigator: ModuleNavigator,
        private val locationServiceManager: LocationServiceManager
) : ViewModel() {
    fun onDoneClick(context: Context, locationName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
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
            }.onFailure {
                Log.e(TAG,"Failed to fetch Lat / Long by location name.")
            }
        }
    }
}
