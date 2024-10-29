package com.interview.weatherforecast.feature.location.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.interview.weatherforecast.core_navigation.Screen

class EnterLocationScreen : Screen() {

    @Composable
    override fun Content() {
        Column(modifier = Modifier.fillMaxSize().background(Color.Green)) {

        }
    }
}
