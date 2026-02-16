package com.minna.medislotapp.ui.screen

// This screen displays all available doctors.
// It shows a vertical list in portrait mode
// and a 2-column grid in landscape mode (responsive layout).

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.minna.medislotapp.data.Doctor
import com.minna.medislotapp.data.SampleData
import com.minna.medislotapp.ui.theme.MediSlotAppTheme

@Composable
fun DoctorsScreen(navController: NavController) {

    // Detect device orientation
    val configuration = LocalConfiguration.current
    val isLandscape =
        configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Scaffold { paddingValues ->

        // If device is in landscape mode → show grid layout
        if (isLandscape) {

            // GRID layout (2 columns)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                // Header spans across both columns
                item(span = { GridItemSpan(2) }) {
                    Text(
                        text = "Our Doctors",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                // Display doctors as cards
                items(SampleData.doctors) { doctor ->
                    DoctorCard(
                        doctor = doctor,
                        navController = navController
                    )
                }
            }

        } else {

            // PORTRAIT → Normal vertical list
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                // Screen title
                item {
                    Text(
                        text = "Our Doctors",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // List of doctors
                items(
                    items = SampleData.doctors,
                    key = { doctor -> doctor.id } // Stable key improves performance
                ) { doctor ->
                    DoctorCard(
                        doctor = doctor,
                        navController = navController
                    )
                }
            }
        }
    }
}

// Reusable card component for each doctor
@Composable
private fun DoctorCard(
    doctor: Doctor,
    navController: NavController
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 500.dp) // Prevents stretching on tablets
            .clickable {
                // Navigate to doctor detail screen
                navController.navigate("doctorDetail/${doctor.id}")
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            // Doctor name
            Text(
                text = doctor.name,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Doctor specialty
            Text(
                text = doctor.specialty,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Experience
            Text(
                text = "Experience: ${doctor.experience}",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Button to view full details
            OutlinedButton(
                onClick = {
                    navController.navigate("doctorDetail/${doctor.id}")
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("View Details")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DoctorsScreenPreview() {
    MediSlotAppTheme {
        DoctorsScreen(rememberNavController())
    }
}