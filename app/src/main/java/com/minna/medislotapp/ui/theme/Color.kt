package com.minna.medislotapp.ui.theme

// This file defines all the colors used in the app.
// It supports both Light Mode and Dark Mode.

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// =========================
// BRAND COLORS
// =========================

// Main primary brand color (used for buttons, highlights)
val TealPrimary = Color(0xFF0F766E)

// Secondary accent color
val TealSecondary = Color(0xFF14B8A6)

// Default text color for light theme
val TextColor = Color(0xFF111111)


// =========================
// LIGHT THEME COLORS
// =========================

// Light background color (main app background)
val LightBackground = Color(0xFFF8FAFC)

// Card and surface background color
val LightSurface = Color(0xFFFFFFFF)

// Border / outline color
val LightOutline = Color(0xFFCBD5E1)


// =========================
// DARK THEME COLORS
// =========================

// Dark background color
val DarkBackground = Color(0xFF1E293B)

// Dark surface (cards, containers)
val DarkSurface = Color(0xFF334155)

// Text color in dark mode
val DarkOnSurface = Color(0xFFF1F5F9)


// =========================
// LIGHT COLOR SCHEME
// =========================

// This defines how colors behave in light mode
val LightColors = lightColorScheme(

    primary = TealPrimary,
    onPrimary = Color.White,

    secondary = TealSecondary,
    onSecondary = Color.White,

    background = LightBackground,
    onBackground = TextColor,

    surface = LightSurface,
    onSurface = TextColor,

    outline = LightOutline
)


// =========================
// DARK COLOR SCHEME
// =========================

// This defines how colors behave in dark mode
val DarkColors = darkColorScheme(

    primary = TealPrimary,
    onPrimary = Color.White,

    secondary = TealSecondary,
    onSecondary = Color.Black,

    background = DarkBackground,
    onBackground = DarkOnSurface,

    surface = DarkSurface,
    onSurface = DarkOnSurface,

    outline = DarkSurface
)