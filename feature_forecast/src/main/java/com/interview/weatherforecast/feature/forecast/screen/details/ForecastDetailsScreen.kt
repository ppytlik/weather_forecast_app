package com.interview.weatherforecast.feature.forecast.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.interview.weatherforecast.core_navigation.Screen
import com.interview.weatherforecast.core_navigation.args.ForecastDetailsScreenArgs
import org.koin.androidx.compose.koinViewModel

class ForecastDetailsScreen(val args: ForecastDetailsScreenArgs) : Screen() {

    @Composable
    override fun Content() {
        val viewModel: ForecastDetailsViewModel = koinViewModel()

        MainContent(
            onBackClicked = { viewModel.onBackClicked() },
            forecastDetailsScreenArgs = args
        )
    }
}

@Composable
fun MainContent(
    forecastDetailsScreenArgs: ForecastDetailsScreenArgs, onBackClicked: () -> Unit
) {

    val body1White = MaterialTheme.typography.body1.copy(
        color = Color.White
    )

    val body2White = MaterialTheme.typography.body1.copy(
        color = Color.White
    )

    Column(
        modifier = Modifier
            .background(Color(0xFF1C1C1C))
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            IconButton(onClick = onBackClicked) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = forecastDetailsScreenArgs.title, style = MaterialTheme.typography.h6.copy(color = Color.White))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "${forecastDetailsScreenArgs.temperatureHigh}°C",
                    style = MaterialTheme.typography.h2,
                    color = Color.White
                )
                Text(
                    text = "Highest value",
                    style = MaterialTheme.typography.body2,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(32.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "${forecastDetailsScreenArgs.temperatureLow}°C",
                    style = MaterialTheme.typography.h2,
                    color = Color.White
                )
                Text(
                    text = "Lowest value",
                    style = MaterialTheme.typography.body2,
                    color = Color.White
                )
            }
        }

        Text(
            text = forecastDetailsScreenArgs.weatherCondition.orEmpty(),
            style = body1White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        WeatherItemWithProgress(
            label = "Average humidity",
            value = "${forecastDetailsScreenArgs.humidity} %",
            progress = (forecastDetailsScreenArgs.humidity?.toFloat() ?: 0f) / 100
        )

        WeatherItem(label = "Average probability of rain", value = "${forecastDetailsScreenArgs.rainProbability} %", body2White)
        WeatherItem(label = "Maximum UV index", value = forecastDetailsScreenArgs.uvIndex.toString(), body2White)
        WeatherItem(label = "Maximum wind speed", value = "${forecastDetailsScreenArgs.windSpeed} km/h", body2White)
        WeatherItem(label = "Average visibility", value = "${forecastDetailsScreenArgs.visibility} m", body2White)
        WeatherItem(label = "Average air pressure", value = "${forecastDetailsScreenArgs.pressure} mbar", body2White)
        WeatherItem(label = "Sunrise", value = forecastDetailsScreenArgs.sunrise, body2White)
        WeatherItem(label = "Sunset", value = forecastDetailsScreenArgs.sunset, body2White)
    }
}

@Composable
fun WeatherItemWithProgress(label: String, value: String, progress: Float) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, style = MaterialTheme.typography.body2, color = Color.White)
            Text(text = value, style = MaterialTheme.typography.body2, color = Color.White)
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFFBB86FC),
            backgroundColor = Color.Gray.copy(alpha = 0.3f)
        )
    }
    Divider(color = Color.Gray.copy(alpha = 0.2f))
}

@Composable
fun WeatherItem(label: String, value: String, body2White: TextStyle) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = body2White)
        Text(text = value, style = body2White)
    }
}

@Preview
@Composable
private fun ScreenPreview() {
    val state = ForecastDetailsScreenArgs(
        title = "Today",
        temperatureHigh = 21.0,
        temperatureLow = 11.0,
        weatherCondition = "Mostly sunny",
        humidity = 75.0,
        rainProbability = 9.0,
        uvIndex = 7,
        windSpeed = 16.0,
        visibility = 927.0,
        pressure = 1024.0,
        sunrise = "06:44 Uhr",
        sunset = "19:14 Uhr"
    )
    MaterialTheme {
        MainContent(state, {})
    }
}
