package com.interview.weatherforecast.feature.location.screen.component

import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import com.interview.weatherforecast.lib.compose.ColorPalette

object ThemeButton {

    @Composable
    fun buttonColorsPrimary() = ButtonDefaults.buttonColors(
            backgroundColor = ColorPalette.buttonFillPrimary,
            contentColor = ColorPalette.buttonTextPrimary,
            disabledBackgroundColor = ColorPalette.itemBackground
    )

    @Composable
    fun buttonColorsSecondary() = ButtonDefaults.buttonColors(
            backgroundColor = ColorPalette.buttonFillSecondary,
            contentColor = ColorPalette.buttonTextSecondary
    )
}