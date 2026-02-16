package com.minna.medislotapp.ui.screen

// This is the first screen users see when they open the app.
// It allows users to navigate to Login or Register screens.

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.minna.medislotapp.ui.theme.MediSlotAppTheme

@Composable
fun WelcomeScreen(navController: NavController) {

    // Scaffold provides the basic Material Design layout structure
    Scaffold {

        // Box is used to center everything on the screen
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {

            // Column arranges items vertically
            Column(
                modifier = Modifier
                    // Prevents stretching on large screens (tablets / landscape)
                    .widthIn(max = 700.dp)
                    .fillMaxWidth()
                    // Allows scrolling in landscape mode
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // App Title
                Text(
                    text = "Welcome to MediSlot",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Short description
                Text(
                    text = "Book your clinic appointments easily.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(48.dp))

                // LOGIN BUTTON
                Button(
                    onClick = {
                        // Navigates to login screen
                        navController.navigate("login")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login")
                }

                Spacer(modifier = Modifier.height(12.dp))

                // REGISTER BUTTON
                OutlinedButton(
                    onClick = {
                        // Navigates to register screen
                        navController.navigate("register")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Register")
                }
            }
        }
    }
}

// Preview allows you to see the screen inside Android Studio
@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    MediSlotAppTheme {
        WelcomeScreen(rememberNavController())
    }
}