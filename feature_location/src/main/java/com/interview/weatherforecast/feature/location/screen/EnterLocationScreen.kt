package com.interview.weatherforecast.feature.location.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.interview.weatherforecast.core_navigation.Screen
import com.interview.weatherforecast.feature.location.viewmodel.EnterLocationViewModel
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
    Column(modifier = Modifier.fillMaxSize().background(Color.Green)) {

        val fieldValue = remember { mutableStateOf("") }

        Text("Search your location")
        Text("Search for your location int following text field and then select it from the list.")

        TextField(
                value = fieldValue.value,
                onValueChange = {
                    fieldValue.value = it
                }
        )

        Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.onDoneClick(fieldValue.value)},
                content = { Text("Done") }
        )
    }
}
