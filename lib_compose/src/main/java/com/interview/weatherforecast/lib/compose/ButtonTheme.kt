package com.interview.weatherforecast.lib.compose

import androidx.compose.material.ButtonDefaults

object ThemeButton {
    val buttonColorsPrimary = ButtonDefaults.buttonColors(
            backgroundColor = ColorPalette.buttonFillPrimary,
            contentColor = ColorPalette.buttonTextPrimary,
            disabledBackgroundColor = ColorPalette.itemBackground
    )

    val buttonColorsSecondary = ButtonDefaults.buttonColors(
            backgroundColor = ColorPalette.buttonFillSecondary,
            contentColor = ColorPalette.buttonTextSecondary
    )
}
