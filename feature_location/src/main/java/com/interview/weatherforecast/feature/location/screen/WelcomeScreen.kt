package com.interview.weatherforecast.feature.location.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.interview.weatherforecast.core_navigation.Screen
import com.interview.weatherforecast.feature.location.viewmodel.WelcomeViewModel
import org.koin.androidx.compose.koinViewModel

class WelcomeScreen() : Screen() {

    @Composable
    override fun Content() {

        val viewModel: WelcomeViewModel = koinViewModel<WelcomeViewModel>()

        MainContent(viewModel)
    }
}

@Composable
private fun MainContent(viewModel: WelcomeViewModel) {
    Column(modifier = Modifier.fillMaxSize().background(color = Color.Cyan)) {

        Text("Welcome to the weather app!")
        Text("To be able to start, we need a location for which the weather information can be retrieved.")

        Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = viewModel::onEnterLocationClick,
                content = { Text("Enter location") }
        )

        Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = viewModel::onUseMyLocationClick,
                content = { Text("Use my location") }
        )
    }
}
