package com.interview.weatherforecast.feature.location.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.interview.weatherforecast.core_navigation.Screen
import com.interview.weatherforecast.feature.location.R
import com.interview.weatherforecast.feature.location.screen.component.Header
import com.interview.weatherforecast.feature.location.screen.component.SubHeader
import com.interview.weatherforecast.feature.location.screen.component.VerticalSpacer
import com.interview.weatherforecast.feature.location.viewmodel.EnterLocationViewModel
import com.interview.weatherforecast.lib.compose.ColorPalette
import org.koin.androidx.compose.koinViewModel

class EnterLocationScreen : Screen() {

    @Composable
    override fun Content() {
        val viewModel: EnterLocationViewModel = koinViewModel<EnterLocationViewModel>()

        MainContent(viewModel)
    }
}

@Composable
private fun MainContent(viewModel: EnterLocationViewModel) {
    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .background(ColorPalette.background)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        val context = LocalContext.current
        val fieldValue = remember { mutableStateOf("") }

        Header(stringResource(R.string.search_your_location_title))
        VerticalSpacer()
        SubHeader(stringResource(R.string.search_for_your_location_message))
        VerticalSpacer()
        OutlinedTextField(
                value = fieldValue.value,
                shape = RoundedCornerShape(2.dp),
                onValueChange = {
                    fieldValue.value = it
                },
                placeholder = { Text("Location", color = ColorPalette.text, modifier = Modifier.padding(start = 2.dp)) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                        textColor = ColorPalette.text,
                        cursorColor = ColorPalette.text,
                        backgroundColor = ColorPalette.background,
                        focusedIndicatorColor = ColorPalette.text,
                        unfocusedIndicatorColor = ColorPalette.text,
                )
        )
        VerticalSpacer()
        Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                        backgroundColor = ColorPalette.buttonFillPrimary,
                        contentColor = ColorPalette.buttonTextPrimary
                ),
                onClick = { viewModel.onDoneClick(context, fieldValue.value) },
                content = { Text(stringResource(R.string.done)) }
        )
    }
}
