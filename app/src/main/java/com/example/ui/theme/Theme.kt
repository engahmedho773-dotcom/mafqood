package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val MafqoodLightColorScheme = lightColorScheme(
    primary = Teal600,
    onPrimary = Color.White,
    primaryContainer = Teal50,
    onPrimaryContainer = Teal700,
    secondary = Navy900,
    onSecondary = Color.White,
    secondaryContainer = BorderColor,
    onSecondaryContainer = Navy900,
    tertiary = Signal600,
    onTertiary = Color.White,
    tertiaryContainer = Signal50,
    onTertiaryContainer = Signal800,
    background = CanvasBg,
    onBackground = Navy900,
    surface = Surface,
    onSurface = Navy900,
    surfaceVariant = ImageBg,
    onSurfaceVariant = TextMuted,
    outline = BorderStrong,
    outlineVariant = BorderColor,
    error = Error600,
    onError = Color.White,
    errorContainer = Error50,
    onErrorContainer = Error600
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MafqoodLightColorScheme,
        typography = Typography,
        content = content
    )
}
