package com.interview.weatherforecast.feature.location.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.interview.weatherforecast.core_navigation.Screen
import com.interview.weatherforecast.feature.location.R
import com.interview.weatherforecast.feature.location.screen.component.Header
import com.interview.weatherforecast.feature.location.screen.component.SubHeader
import com.interview.weatherforecast.feature.location.screen.component.ThemeButton
import com.interview.weatherforecast.feature.location.screen.component.VerticalSpacer
import com.interview.weatherforecast.feature.location.viewmodel.WelcomeViewModel
import com.interview.weatherforecast.lib.compose.ColorPalette
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
    Column(
            modifier = Modifier
                    .fillMaxSize().background(ColorPalette.background)
                    .padding(horizontal = 16.dp, vertical = 8.dp)) {

        Header(stringResource(R.string.welcome_to_the_weather_app))
        VerticalSpacer()
        SubHeader(stringResource(R.string.welcome_rationale))
        VerticalSpacer()
        Button(
                border = BorderStroke(width = 1.dp, color = ColorPalette.buttonStroke),
                shape = RoundedCornerShape(8.dp),
                colors = ThemeButton.buttonColorsSecondary(),
                modifier = Modifier.fillMaxWidth(),
                onClick = viewModel::onEnterLocationClick,
                content = { Text(stringResource(R.string.enter_location), color = ColorPalette.buttonTextSecondary) }
        )
        VerticalSpacer()
        Button(
                shape = RoundedCornerShape(8.dp),
                colors = ThemeButton.buttonColorsPrimary(),
                modifier = Modifier.fillMaxWidth(),
                onClick = viewModel::onUseMyLocationClick,
                content = { Text(stringResource(R.string.use_my_location), color = ColorPalette.buttonTextPrimary) }
        )
    }
}
