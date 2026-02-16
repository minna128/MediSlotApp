package com.minna.medislotapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * This file defines all text styles used in the app.
 *
 * Instead of setting font size and font family
 * separately in every screen, we define them here once.
 *
 * This keeps the app:
 * - Consistent
 * - Professional
 * - Easy to maintain
 */

val AppTypography = Typography(

    // Headline styles (Used for main titles like Welcome, Doctor Name)
    headlineLarge = TextStyle(
        fontFamily = RobotoSerif,     // Serif font for important headings
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp              // Large text size
    ),

    headlineMedium = TextStyle(
        fontFamily = RobotoSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),

    titleLarge = TextStyle(
        fontFamily = RobotoSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),

    // Body text styles (Used for normal content text)
    bodyLarge = TextStyle(
        fontFamily = RobotoFlex,      // Clean modern font for readability
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = RobotoFlex,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),

    // Label text (Buttons, small actions like Cancel)
    labelLarge = TextStyle(
        fontFamily = RobotoFlex,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)