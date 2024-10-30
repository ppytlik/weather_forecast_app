package com.interview.weatherforecast.feature.location.viewmodel

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.weatherforecast.core.location.permission.LocationPermissionManager
import com.interview.weatherforecast.core.location.service.LocationServiceManager
import com.interview.weatherforecast.core_navigation.args.ForecastListScreenArgs
import com.interview.weatherforecast.feature.location.ModuleNavigator
import com.interview.weatherforecast.feature.location.state.AskForLocationScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "AskForLocationViewModel"
private const val PERMISSION_REQUEST_CODE = 0x1234
private val permissions = listOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION).toTypedArray()

class AskForLocationViewModel(
        val moduleNavigator: ModuleNavigator,
        val locationPermissionManager: LocationPermissionManager,
        val locationServiceManager: LocationServiceManager
) : ViewModel() {

    private val screenStateFlow: MutableStateFlow<AskForLocationScreenState> = MutableStateFlow(AskForLocationScreenState())

    fun getScreenStateFlow(): StateFlow<AskForLocationScreenState> = screenStateFlow

    fun onDoneClick(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val location = locationServiceManager.getCurrentLocation(context = context)
                withContext(Dispatchers.Main) {
                    moduleNavigator.openForecastScreen(
                            ForecastListScreenArgs(
                                    locationName = location.name,
                                    latitude = location.latitude,
                                    longitude = location.longitude
                            )
                    )
                }
            }.onFailure {
                Log.e(TAG,"Failed to request location.")
            }
        }
    }

    fun askForPermissions(context: Context) = locationPermissionManager.askForPermissions(context, permissions, PERMISSION_REQUEST_CODE)

    fun checkLocationPermission(context: Context) {
        val fine = locationPermissionManager.checkFineLocationPermission(context)
        val coarse = locationPermissionManager.checkCoarseLocationPermission(context)

        screenStateFlow.update {
            screenStateFlow.value.copy(
                    hasLocationServiceAccess = fine && coarse,
                    hasFineLocationPermission = fine,
                    hasCoarseLocationPermission = coarse
            )
        }
    }
}
