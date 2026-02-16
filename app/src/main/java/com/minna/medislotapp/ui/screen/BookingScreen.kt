package com.minna.medislotapp.ui.screen

// This screen allows the user to confirm a selected appointment
// and creates a new Appointment object when the button is clicked.

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.minna.medislotapp.data.SampleData
import com.minna.medislotapp.models.Appointment
import java.util.UUID

@Composable
fun BookingScreen(
    navController: NavController,        // Used for screen navigation
    doctorName: String,                  // Doctor selected from previous screen
    time: String,                        // Time slot selected
    onConfirm: (Appointment) -> Unit     // Callback to add appointment to ViewModel
) {

    Scaffold { paddingValues ->

        // Box centers content horizontally and keeps layout clean in landscape
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {

            // Column holds the whole UI vertically
            Column(
                modifier = Modifier
                    .widthIn(max = 600.dp)              // Prevents stretching in landscape
                    .verticalScroll(rememberScrollState()) // Enables scrolling
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Screen Title
                Text(
                    text = "Confirm Appointment",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Card showing appointment summary
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Text("Doctor: $doctorName")
                        Text("Time: $time")
                        Text("Date: May 30, 2026")
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Confirm Button
                Button(
                    onClick = {

                        // Create a new Appointment object
                        val newAppointment = Appointment(
                            id = UUID.randomUUID().toString(), // Unique ID
                            date = "May 30, 2026",
                            time = time,
                            doctorName = doctorName,
                            doctorSpecialty = SampleData.doctors
                                .find { it.name == doctorName }?.specialty ?: "",
                            clinic = SampleData.doctors
                                .find { it.name == doctorName }?.clinic ?: ""
                        )

                        // Send appointment to ViewModel
                        onConfirm(newAppointment)

                        // Navigate back to Home
                        navController.navigate("home") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Confirm & Book")
                }
            }
        }
    }
}
