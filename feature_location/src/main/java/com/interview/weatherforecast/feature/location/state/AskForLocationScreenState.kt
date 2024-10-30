package com.interview.weatherforecast.feature.location.state

data class AskForLocationScreenState(
        val hasFineLocationPermission: Boolean = false,
        val hasCoarseLocationPermission: Boolean = false,
        val hasLocationServiceAccess: Boolean = false
)
