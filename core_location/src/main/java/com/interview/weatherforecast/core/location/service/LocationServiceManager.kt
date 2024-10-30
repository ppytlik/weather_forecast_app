package com.interview.weatherforecast.core.location.service

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.interview.wheatherforecast.lib.domain.model.LocationData
import java.util.Locale
import kotlin.coroutines.suspendCoroutine

class LocationServiceManager {

    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(context: Context): LocationData {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        val location = suspendCoroutine<Location> { continuation ->
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                    continuation.resumeWith(Result.success(location))
            }
        }

        return location.mapToDomainLocation(context)
    }

    private suspend fun getLocationName(location: Location, context: Context): String {
        return suspendCoroutine<String> { continuation ->
            val geoCoder = Geocoder(context, Locale.getDefault())

            geoCoder.getFromLocation(location.latitude, location.longitude, 1) { results ->
                val locationName = results.firstOrNull()?.locality ?: "N/A"
                continuation.resumeWith(Result.success(locationName))
            }
        }
    }

    private suspend fun Location.mapToDomainLocation(context: Context) = LocationData(
            name = getLocationName(this, context),
            latitude = latitude.toFloat(),
            longitude = longitude.toFloat()
    )
}