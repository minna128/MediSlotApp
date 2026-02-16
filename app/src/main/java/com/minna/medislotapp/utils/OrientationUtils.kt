package com.minna.medislotapp.utils

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

/**
 * This utility function checks if the device
 * is currently in landscape orientation.
 *
 * We created this as a separate function so we can:
 * - Reuse it in multiple screens
 * - Keep screen files clean
 * - Avoid repeating the same orientation logic everywhere
 */

@Composable
fun isLandscape(): Boolean {

    // LocalConfiguration gives us current device info
    // like orientation, screen size, etc.
    val configuration = LocalConfiguration.current

    // We compare the current orientation
    // with Android's LANDSCAPE constant
    return configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE
}