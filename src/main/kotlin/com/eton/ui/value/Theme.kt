package com.eton.ui.value

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Color set
val LightTheme = lightColors(
    primary = Color.White,
    onPrimary = R.color.ElephantBlue,
    secondary = Color.Black,
    onSecondary = Color.White,
    error = R.color.WatermelonRed
)

val DarkTheme = darkColors(
    primary = R.color.PictonBlue,
    onPrimary = Color.White,
    secondary = R.color.ElephantBlue,
    onSecondary = Color.White,
    surface = R.color.BigStoneBlue,
    error = R.color.WatermelonRed
)

@Composable
fun EtonTheme(
    isDark: Boolean = false, // TODO: If you want to support both light theme and dark theme, you'll need to implement it manually.
    content: @Composable ColumnScope.() -> Unit,
) {
    MaterialTheme(
        colors = if (isDark) DarkTheme else LightTheme,
        typography = EtonTypography
    ) {
        Surface {
            Column {
                content()
            }
        }
    }
}