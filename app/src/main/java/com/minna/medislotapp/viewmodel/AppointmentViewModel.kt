package com.minna.medislotapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.minna.medislotapp.data.SampleData
import com.minna.medislotapp.models.Appointment
import com.minna.medislotapp.models.AppointmentStatus

/**
 * AppointmentViewModel
 *
 * This class manages the appointment data for the entire app.
 *
 * It survives configuration changes (like screen rotation)
 * and acts as a single source of truth for appointment state.
 */

class AppointmentViewModel : ViewModel() {

    /**
     * Private mutable list that holds appointments.
     *
     * We use mutableStateListOf so Compose automatically
     * recomposes the UI whenever the list changes.
     *
     * We initialize it with sample data.
     */
    private val _appointments = mutableStateListOf<Appointment>().apply {
        addAll(SampleData.appointments)
    }

    /**
     * Public read-only list exposed to the UI.
     *
     * Screens can read the data,
     * but cannot directly modify it.
     */
    val appointments: List<Appointment> = _appointments

    /**
     * Adds a new appointment to the list.
     *
     * When this is called:
     * - The list updates
     * - Compose automatically recomposes HomeScreen
     */
    fun addAppointment(appointment: Appointment) {
        _appointments.add(appointment)
    }

    /**
     * Removes an appointment from the list.
     *
     * Used when the user presses "Cancel".
     * UI automatically updates due to state observation.
     */
    fun cancelAppointment(appointment: Appointment) {
        val index = _appointments.indexOf(appointment)
        if (index != -1) {
            _appointments[index] =
                appointment.copy(status = AppointmentStatus.CANCELLED)
        }
    }
}