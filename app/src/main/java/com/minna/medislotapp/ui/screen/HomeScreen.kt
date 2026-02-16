package com.minna.medislotapp.ui.screen

// This screen shows the user's upcoming appointments.
// It supports portrait and landscape layouts.
// It also includes animation when cancelling an appointment.

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.minna.medislotapp.data.SampleData.appointments
import com.minna.medislotapp.models.Appointment
import com.minna.medislotapp.models.AppointmentStatus
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay


@Composable
fun HomeScreen(
    navController: NavController,
    appointments: List<Appointment>,
    onCancel: (Appointment) -> Unit
) {

    // Detect device orientation
    val configuration = LocalConfiguration.current
    val isLandscape =
        configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    // Snackbar for showing cancel confirmation message
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->

        // Center content horizontally on large screens
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {

            // If landscape → grid layout
            if (isLandscape) {
                LandscapeLayout(
                    appointments,
                    onCancel,
                    snackbarHostState,
                    scope
                )
            } else {
                // Portrait → vertical list
                PortraitLayout(
                    appointments,
                    onCancel,
                    snackbarHostState,
                    scope
                )
            }
        }
    }
}

@Composable
private fun PortraitLayout(
    appointments: List<Appointment>,
    onCancel: (Appointment) -> Unit,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
) {

    // LazyColumn = scrollable vertical list
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .widthIn(max = 700.dp), // Prevents stretching on tablets
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        // Header section
        item { ScreenHeader() }

        // If no appointments → show empty state
        if (appointments.isEmpty()) {
            item { EmptyState() }
        } else {

            // Show each appointment
            items(
                items = appointments,
                key = { it.id } // Stable key for better performance
            ) { appointment ->

                // Animated card when cancelling
                AnimatedAppointmentCard(
                    appointment = appointment,
                    onCancel = {
                        onCancel(appointment)

                        // Show snackbar message
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "Appointment cancelled successfully"
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun LandscapeLayout(
    appointments: List<Appointment>,
    onCancel: (Appointment) -> Unit,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
) {

    // Grid layout with 2 columns
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .widthIn(max = 900.dp),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Header spans full width
        item(span = { GridItemSpan(maxLineSpan) }) {
            ScreenHeader()
        }

        if (appointments.isEmpty()) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                EmptyState()
            }
        } else {

            items(
                items = appointments,
                key = { it.id }
            ) { appointment ->

                AnimatedAppointmentCard(
                    appointment = appointment,
                    onCancel = {
                        onCancel(appointment)

                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "Appointment cancelled successfully"
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun AnimatedAppointmentCard(
    appointment: Appointment,
    onCancel: () -> Unit
) {
    var visible by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    val upcomingAppointments =
        appointments.filter { it.status ==
                AppointmentStatus.UPCOMING }


    AnimatedVisibility(
        visible = visible,
        exit = shrinkVertically(
            animationSpec = tween(800)
        ) + fadeOut(
            animationSpec = tween(800)
        )
    ) {
        AppointmentCard(
            appointment = appointment,
            onCancelClick = {
                visible = false

                scope.launch {
                    delay(800)   // Wait for animation to finish
                    onCancel()
                }
            }
        )
    }
}
@Composable
fun AppointmentCard(
    appointment: Appointment,
    onCancelClick: (() -> Unit)?
) {

    // Card component (required in marking scheme)
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            // Doctor name
            Text(
                text = appointment.doctorName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            // Specialty
            Text(
                text = appointment.doctorSpecialty,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Date
            Text(
                text = appointment.date,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Time
            Text(
                text = appointment.time,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(12.dp))


            // Cancel button (only for upcoming appointments)
            if (appointment.status == AppointmentStatus.UPCOMING &&
                onCancelClick != null
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = onCancelClick
                    ) {
                        Text(
                            text = "Cancel",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
        }
    }


@Composable
private fun ScreenHeader() {
    Column {
        Text(
            text = "Welcome back, Minna",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Here are your upcoming appointments",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun EmptyState() {
    Text(
        text = "You don’t have any upcoming appointments.",
        style = MaterialTheme.typography.bodyLarge
    )
}
