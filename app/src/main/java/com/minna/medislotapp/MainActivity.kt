package com.minna.medislotapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.minna.medislotapp.ui.component.AppNavGraph
import com.minna.medislotapp.ui.theme.MediSlotAppTheme

/**
 * MainActivity
 *
 * This is the entry point of the application.
 * Android launches this activity when the app starts.
 */

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Enables edge-to-edge layout.
         * This allows content to draw behind system bars
         * (status bar and navigation bar) for a modern look.
         */
        enableEdgeToEdge()

        /**
         * setContent is where Jetpack Compose UI starts.
         * Instead of using XML layouts, we define UI in Kotlin.
         */
        setContent {

            /**
             * Wrap the entire app inside our custom theme.
             *
             * This applies:
             * - Light/Dark mode automatically
             * - Custom colors
             * - Custom typography
             */
            MediSlotAppTheme {

                /**
                 * AppNavGraph controls all screen navigation.
                 *
                 * It contains:
                 * - Welcome screen
                 * - Login/Register
                 * - Home
                 * - Doctors
                 * - Profile
                 * - Booking flow
                 */
                AppNavGraph()
            }
        }
    }
}