package com.minna.medislotapp.ui.component

// This file controls navigation between all screens in the app.
// It also connects the ViewModel (appointments data) with the UI screens.

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.minna.medislotapp.data.SampleData
import com.minna.medislotapp.ui.screen.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.minna.medislotapp.viewmodel.AppointmentViewModel

@Composable
fun AppNavGraph() {

    // Controls navigation between screens
    val navController = rememberNavController()

    // ViewModel stores appointments safely across orientation changes
    val appointmentViewModel: AppointmentViewModel = viewModel()

    // These screens will show the bottom navigation bar
    val mainScreenRoutes = setOf("home", "doctors", "profile")

    // Observe current route to decide when to show bottom nav
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,

        // Show bottom navigation only on main screens
        bottomBar = {
            if (currentRoute in mainScreenRoutes) {
                BottomNavBar(navController)
            }
        }
    ) { innerPadding ->

        // NavHost contains all screen destinations
        NavHost(
            navController = navController,
            startDestination = "welcome",
            modifier = Modifier.padding(innerPadding)
        ) {

            // ---------------- AUTH SCREENS ----------------

            composable("welcome") { WelcomeScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("register") { RegisterScreen(navController) }

            // ---------------- HOME SCREEN ----------------

            composable("home") {
                HomeScreen(
                    navController = navController,

                    // Pass appointments from ViewModel
                    appointments = appointmentViewModel.appointments,

                    // When cancel is clicked → remove from ViewModel
                    onCancel = { appointment ->
                        appointmentViewModel.removeAppointment(appointment)
                    }
                )
            }

            // ---------------- DOCTORS SCREEN ----------------

            composable("doctors") {
                DoctorsScreen(navController)
            }

            // ---------------- PROFILE SCREEN ----------------

            composable("profile") {
                ProfileScreen(navController)
            }

            // ---------------- DOCTOR DETAIL ----------------

            composable(
                route = "doctorDetail/{doctorId}",
                arguments = listOf(
                    navArgument("doctorId") { type = NavType.IntType }
                )
            ) { backStackEntry ->

                // Get doctorId from navigation arguments
                val doctorId = backStackEntry.arguments?.getInt("doctorId")

                // Find the doctor from SampleData list
                val doctor = SampleData.doctors.find { it.id == doctorId }

                // If doctor exists, open detail screen
                if (doctor != null) {
                    DoctorDetailScreen(
                        doctor = doctor,
                        navController = navController
                    )
                }
            }

            // ---------------- BOOKING SCREEN ----------------

            composable(
                route = "booking/{doctorName}/{time}",
                arguments = listOf(
                    navArgument("doctorName") { type = NavType.StringType },
                    navArgument("time") { type = NavType.StringType }
                )
            ) { backStackEntry ->

                // Get values passed from DoctorDetailScreen
                val doctorName =
                    backStackEntry.arguments?.getString("doctorName") ?: ""

                val time =
                    backStackEntry.arguments?.getString("time") ?: ""

                BookingScreen(
                    navController = navController,
                    doctorName = doctorName,
                    time = time,

                    // When booking confirmed → add to ViewModel
                    onConfirm = { newAppointment ->
                        appointmentViewModel.addAppointment(newAppointment)
                    }
                )
            }
        }
    }
}