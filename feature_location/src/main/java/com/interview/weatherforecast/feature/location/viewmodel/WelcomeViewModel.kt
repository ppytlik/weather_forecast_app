package com.interview.weatherforecast.feature.location.viewmodel

import androidx.lifecycle.ViewModel
import com.interview.weatherforecast.feature.location.ModuleNavigator

class WelcomeViewModel(
        private val moduleNavigator: ModuleNavigator
) : ViewModel() {

    fun onEnterLocationClick() = moduleNavigator.openEnterLocationScreen()

    fun onUseMyLocationClick() = moduleNavigator.openAskForLocationScreen()
}
