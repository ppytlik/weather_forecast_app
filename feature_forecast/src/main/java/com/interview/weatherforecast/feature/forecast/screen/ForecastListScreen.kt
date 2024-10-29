package com.interview.weatherforecast.feature.forecast.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.interview.weatherforecast.core_navigation.Screen
import com.interview.weatherforecast.feature.forecast.state.ForecastListState
import com.interview.weatherforecast.feature.forecast.viewmodel.ForecastListViewModel
import org.koin.androidx.compose.koinViewModel

class ForecastListScreen : Screen() {

    @Composable
    override fun Content() {
        val viewModel: ForecastListViewModel = koinViewModel()

        val state = viewModel.getScreenStateFlow().collectAsState()

        MainContent(viewModel, state.value)
    }
}

@Composable
private fun MainContent(viewModel: ForecastListViewModel, screenState: ForecastListState) {
    Column(modifier = Modifier.fillMaxSize().background(Color.Green)) {
        Text("Search your location")
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Location")
            Button(
                    onClick = viewModel::onLocationButtonClick,
                    content = { Text(screenState.locationName) }
            )
        }
    }
}
