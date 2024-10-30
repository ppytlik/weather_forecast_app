package com.interview.weatherforecast.feature.location.screen.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.interview.weatherforecast.lib.compose.ColorPalette
import com.interview.weatherforecast.lib.compose.ThemeSize

@Composable
fun Header(text: String) {
    Text(text, color = ColorPalette.text, fontSize = ThemeSize.header)
}

@Composable
fun SubHeader(text: String) {
    Text(text, color = ColorPalette.text, fontSize = ThemeSize.subheader)
}

@Composable
fun Label(text: String) {
    Text(text, color = ColorPalette.text, fontSize = ThemeSize.subheader)
}
