package com.interview.weatherforecast.feature.location.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.interview.weatherforecast.core_navigation.Screen
import com.interview.weatherforecast.feature.location.R
import com.interview.weatherforecast.feature.location.screen.component.Header
import com.interview.weatherforecast.feature.location.screen.component.Label
import com.interview.weatherforecast.feature.location.screen.component.SubHeader
import com.interview.weatherforecast.feature.location.screen.component.ThemeButton
import com.interview.weatherforecast.feature.location.screen.component.VerticalSpacer
import com.interview.weatherforecast.feature.location.state.AskForLocationScreenState
import com.interview.weatherforecast.feature.location.viewmodel.AskForLocationViewModel
import com.interview.weatherforecast.lib.compose.ColorPalette
import org.koin.androidx.compose.koinViewModel

class AskForLocationScreen : Screen() {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<AskForLocationViewModel>()
        val state = viewModel.getScreenStateFlow().collectAsState()
        MainContent(viewModel, state.value)
    }

    @Composable
    private fun MainContent(viewModel: AskForLocationViewModel, state: AskForLocationScreenState) {
        Column(modifier = Modifier.fillMaxSize().background(ColorPalette.background).padding(horizontal = 16.dp, vertical = 8.dp)) {
            val context = LocalContext.current
            viewModel.checkLocationPermission(context)

            Header(stringResource(R.string.use_my_location_title))
            VerticalSpacer()
            SubHeader(stringResource(R.string.grant_location_authorisations))
            VerticalSpacer()
            SubHeader(stringResource(R.string.to_be_able_to_access_your_location_the_app_requires_authorisation_to_do_so))
            SubHeader(stringResource(R.string.your_phone_must_also_support_this))
            VerticalSpacer()

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Label(stringResource(R.string.location_service))
                Button(
                        onClick = {},
                        colors = ThemeButton.buttonColorsSecondary(),
                ) {
                    if (state.hasLocationServiceAccess) {
                        Text(stringResource(R.string.okay))
                    } else {
                        viewModel.askForPermissions(context)
                        Text(stringResource(R.string.ausgeschalten))
                    }
                }
            }
            VerticalSpacer()
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Label(stringResource(R.string.location_permission))
                Button(
                        onClick = {},
                        colors = ThemeButton.buttonColorsSecondary(),
                ) {
                    if (state.hasFineLocationPermission || state.hasCoarseLocationPermission) {
                        Text(stringResource(R.string.okay))
                    } else {
                        viewModel.askForPermissions(context)
                        Text(stringResource(R.string.nicht_gewahrt))
                    }
                }
            }
            VerticalSpacer()
            Button(
                    border = BorderStroke(width = 1.dp, color = ColorPalette.buttonStroke),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = ThemeButton.buttonColorsSecondary(),
                    onClick = { viewModel.checkLocationPermission(context) },
            ) {
                Text(stringResource(R.string.check_again))
            }
            VerticalSpacer()
            Button(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = ThemeButton.buttonColorsPrimary(),
                    onClick = { viewModel.onDoneClick(context) },
                    enabled = state.hasLocationServiceAccess
            ) {
                Text(stringResource(R.string.done))
            }
        }
    }
}
