package com.minna.medislotapp.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.minna.medislotapp.R

/**
 * This file defines the custom fonts used in the app.
 * The font files must be placed inside: res/font/
 */


// =========================
// ROBOTO SERIF FONT FAMILY
// =========================

// This creates a font family called RobotoSerif.
// It includes different font weights (Normal and Bold).
// Compose automatically switches weight when needed.

val RobotoSerif = FontFamily(
    Font(
        R.font.robotoserif_regular,   // normal text version
        FontWeight.Normal
    ),
    Font(
        R.font.robotoserif_bold,      // bold version
        FontWeight.Bold
    )
)


// =========================
// ROBOTO FLEX FONT FAMILY
// =========================

// This creates another font family called RobotoFlex.
// Currently it only has one weight (Normal).

val RobotoFlex = FontFamily(
    Font(
        R.font.robotoflex_regular,
        FontWeight.Normal
    )
)