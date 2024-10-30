package com.interview.weatherforecast.core.location.permission

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class LocationPermissionManager {

    fun askForPermissions(context: Context, permissions: Array<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(context as Activity, permissions, requestCode)
    }

    fun checkFineLocationPermission(context: Context) =
            ContextCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

    fun checkCoarseLocationPermission(context: Context) =
            ContextCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
}
