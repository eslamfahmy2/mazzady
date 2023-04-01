package com.testapp.presentation.components.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Primary02 = Color(0xFF6E6F70)
val BackgroundLight = Color(0xFF00A4E6)
val Primary05 = Color(0xFFE91E63)
val onSer = Color(0xFF838BAA)

val gradientEnd = Color(0xD4002762)
val backgroundClose = Color(0xFFE5E5E5)

val yellow = Color(0xFFFFC107)

//dark theme colors
val DarkColorPalette = darkColors(
    primary = Primary05,
    secondary = yellow,
    background = BackgroundLight,
    onPrimary = Primary02,
    onSurface = gradientEnd,

    )

//light theme colors
val LightColorPalette = lightColors(
    primary = Primary05,
    secondary = yellow,
    background = BackgroundLight,
    onPrimary = Primary02,
    onSurface = gradientEnd,

    )

