package com.minna.medislotapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

/**
 * This is the main theme wrapper of the entire app.
 * It controls:
 * - Light / Dark mode
 * - Color scheme
 * - Typography (fonts)
 *
 * Every screen in the app is wrapped inside this theme.
 */

@Composable
fun MediSlotAppTheme(
    // Automatically detects if device is in dark mode
    useDarkTheme: Boolean = isSystemInDarkTheme(),

    // All UI content of the app will be passed here
    content: @Composable () -> Unit
) {

    // If device is in dark mode → use DarkColors
    // Otherwise → use LightColors
    val colors = if (useDarkTheme) {
        DarkColors
    } else {
        LightColors
    }

    // Applies Material 3 theme settings to the entire app
    MaterialTheme(
        colorScheme = colors,       // Applies light/dark colors
        typography = AppTypography, // Applies custom fonts
        content = content           // Displays the UI content
    )
}
