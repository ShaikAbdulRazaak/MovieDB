package com.razzaaq.moviedb.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = DuskBlue,
    onPrimary = AppWhite,
    secondary = PumpkinSpice,
    onSecondary = AppWhite,
    tertiary = SandyBrown,
    background = Color(0xFF1C1B1F), // Standard dark background
    surface = Color(0xFF1C1B1F),
    onBackground = AppWhite,
    onSurface = AppWhite,
    surfaceVariant = DuskBlue.copy(alpha = 0.2f),
    onSurfaceVariant = ApricotCream
)

private val LightColorScheme = lightColorScheme(
    primary = DuskBlue,
    onPrimary = AppWhite,
    secondary = PumpkinSpice,
    onSecondary = AppWhite,
    tertiary = SandyBrown,
    background = AppWhite,
    surface = AppWhite,
    onPrimaryContainer = DuskBlue,
    surfaceVariant = ApricotCream,
    onSurfaceVariant = DuskBlue
)

@Composable
fun MovieDBTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}