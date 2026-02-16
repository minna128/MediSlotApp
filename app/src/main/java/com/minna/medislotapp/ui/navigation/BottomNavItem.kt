package com.minna.medislotapp.ui.navigation

// This file defines the bottom navigation items (Home, Doctors, Profile).
// It acts as a single source of truth for bottom navigation routes.

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Sealed class for bottom navigation items.
 *
 * Why sealed class?
 * → It restricts navigation items to only the ones defined here.
 * → Prevents mistakes like wrong route names.
 */
sealed class BottomNavItem(
    val route: String,        // Navigation route name
    val label: String,        // Text shown under the icon
    val icon: ImageVector     // Icon shown in bottom navigation
) {

    // Home navigation item
    object Home : BottomNavItem(
        route = "home",
        label = "Home",
        icon = Icons.Default.Home
    )

    // Doctors navigation item
    object Doctors : BottomNavItem(
        route = "doctors",
        label = "Doctors",
        icon = Icons.Default.LocalHospital
    )

    // Profile navigation item
    object Profile : BottomNavItem(
        route = "profile",
        label = "Profile",
        icon = Icons.Default.Person
    )
}