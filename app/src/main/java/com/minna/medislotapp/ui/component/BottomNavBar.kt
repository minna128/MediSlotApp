package com.minna.medislotapp.ui.component

// This file contains the Bottom Navigation Bar used in the app.
// It allows users to switch between main screens (Home, Doctors, Profile).

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.minna.medislotapp.ui.navigation.BottomNavItem

@Composable
fun BottomNavBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    // List of screens that appear in the bottom navigation
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Doctors,
        BottomNavItem.Profile
    )

    // Observes current screen to highlight selected tab
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Material Design bottom navigation container
    NavigationBar(
        modifier = modifier.navigationBarsPadding(),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 6.dp
    ) {

        // Create one navigation item for each screen
        items.forEach { item ->
            NavigationBarItem(

                // Highlight item if it matches current route
                selected = currentRoute?.startsWith(item.route) == true,

                // Navigate when user clicks a tab
                onClick = {
                    navController.navigate(item.route) {

                        // Prevent building a large back stack
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        // Avoid duplicate screens
                        launchSingleTop = true

                        // Restore previous state when switching tabs
                        restoreState = true
                    }
                },

                // Icon for the tab
                icon = { Icon(item.icon, contentDescription = item.label) },

                // Text label under icon
                label = { Text(item.label) },

                // Always show label text
                alwaysShowLabel = true,

                // Colors for selected and unselected states
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    }
}