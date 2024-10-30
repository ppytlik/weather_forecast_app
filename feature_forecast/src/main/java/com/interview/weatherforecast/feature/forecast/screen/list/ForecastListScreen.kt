package com.interview.weatherforecast.feature.forecast.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.interview.weatherforecast.core_navigation.Screen
import com.interview.weatherforecast.core_navigation.args.ForecastListScreenArgs
import com.interview.weatherforecast.feature.forecast.domain.usecase.AvailableForecast
import org.koin.androidx.compose.koinViewModel

class ForecastListScreen(private val args: ForecastListScreenArgs) : Screen() {

    @Composable
    override fun Content() {
        val viewModel: ForecastListViewModel = koinViewModel()
        viewModel.loadData(args = args)

        val state = viewModel.getScreenStateFlow().collectAsState()
        MainContent(
            onLocationClick = { viewModel.onLocationButtonClick() },
            onItemClicked = { item: AvailableForecast -> viewModel.onForecastItemClick(item) },
            forecastListState = state.value
        )
    }
}

@Composable
private fun MainContent(
    onLocationClick: () -> Unit,
    onItemClicked: (AvailableForecast) -> Unit,
    forecastListState: ForecastListState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1C1C1C))
            .padding(horizontal = 12.dp)
    ) {
        Text("Search your location")
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Location")
            Button(
                onClick = onLocationClick,
                content = { Text(forecastListState.locationName) }
            )
        }

        if (forecastListState.errorText != null) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    text = forecastListState.errorText
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(items = forecastListState.items) { item ->
                    ForecastListItem(item = item, onItemClicked = onItemClicked)
                }
            }
        }
    }
}

@Composable
private fun ForecastListItem(item: AvailableForecast, onItemClicked: (AvailableForecast) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(8.dp))
            .background(color = Color(0xFF2C2C2C), shape = RoundedCornerShape(8.dp))
            .clickable(onClick = {
                onItemClicked(item)
            })
            .padding(vertical = 4.dp, horizontal = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Text(
                text = item.displayForecastText,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.displayDate,
                style = MaterialTheme.typography.body2.copy(color = Color.White)
            )
        }
    }
}

@Preview
@Composable
private fun ScreenPreview() {
    MaterialTheme {
        MainContent(
            onLocationClick = {},
            onItemClicked = {},
            forecastListState = ForecastListState("test", errorText = "Error")
        )
    }
}

@Preview
@Composable
private fun ListPreview() {
    MaterialTheme {
        val messages = (0..10).map {
            AvailableForecast("Test", "Test", null)
        }
        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(items = messages) { item ->
                ForecastListItem(
                    item = item,
                    onItemClicked = {}
                )
            }
        }
    }
}
