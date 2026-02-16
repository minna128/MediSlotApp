package com.minna.medislotapp.models

// This data class represents one appointment in the app.
// It defines the structure of appointment data.
// All appointment-related screens use this model.
data class Appointment(

    // Unique ID for each appointment.
    // Used for animations, deletion, and list updates.
    val id: String,

    // Appointment date (e.g., May 25, 2026)
    val date: String,

    // Appointment time (e.g., 10:00 AM)
    val time: String,

    // Doctor’s full name
    val doctorName: String,

    // Doctor’s specialty (e.g., Cardiologist)
    val doctorSpecialty: String,

    // Clinic location name
    val clinic: String,

    // Default status property inside Appointment data class
// This means every new appointment will automatically start as UPCOMING
    val status: AppointmentStatus = AppointmentStatus.UPCOMING
)


// Enum class defines the ONLY allowed status values for an appointment
// Using enum instead of String prevents spelling mistakes and invalid values
enum class AppointmentStatus {

    // Appointment is scheduled and not yet completed
    UPCOMING,

    // Appointment has already happened
    COMPLETED,

    // Appointment was cancelled by the user
    CANCELLED
}