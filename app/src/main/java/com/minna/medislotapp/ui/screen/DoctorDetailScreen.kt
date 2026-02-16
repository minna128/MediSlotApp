package com.minna.medislotapp.ui.screen

// This screen shows detailed information about a selected doctor.
// It also allows the user to choose a time slot and proceed to booking.

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.minna.medislotapp.data.Doctor
import com.minna.medislotapp.data.SampleData
import com.minna.medislotapp.ui.theme.MediSlotAppTheme

@Composable
fun DoctorDetailScreen(
    doctor: Doctor,              // Doctor object passed from DoctorsScreen
    navController: NavController // Used for navigation
) {

    // Static sample time slots
    val timeSlots = listOf("10:00 AM", "11:00 AM", "02:00 PM", "04:00 PM")

    Scaffold { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()) // Enables scrolling in landscape
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            // Doctor name (large heading)
            Text(
                text = doctor.name,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Card displaying doctor details
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    // Display doctor information using reusable row component
                    InfoRow(label = "Specialty", value = doctor.specialty)
                    HorizontalDivider()
                    InfoRow(label = "Experience", value = doctor.experience)
                    HorizontalDivider()
                    InfoRow(label = "Consultation Fee", value = doctor.consultationFee)
                }
            }

            // Section title
            Text(
                text = "Available Time Slots",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            // Horizontal scrollable row of time slot buttons
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(timeSlots) { time ->

                    Button(
                        onClick = {
                            // Encode doctor name for safe navigation (handles spaces)
                            val encodedName = Uri.encode(doctor.name)

                            // Navigate to BookingScreen with doctor name and time
                            navController.navigate("booking/$encodedName/$time")
                        },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = time)
                    }
                }
            }
        }
    }
}

/*
Reusable row component for displaying label-value pairs
Example:
Specialty     Dermatologist
*/
@Composable
private fun InfoRow(label: String, value: String) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

// Preview for design testing
@Preview(showBackground = true)
@Composable
fun DoctorDetailScreenPreview() {
    val sampleDoctor = SampleData.doctors.first()

    MediSlotAppTheme {
        DoctorDetailScreen(
            doctor = sampleDoctor,
            navController = rememberNavController()
        )
    }
}