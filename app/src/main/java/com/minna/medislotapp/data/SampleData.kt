package com.minna.medislotapp.data

import com.minna.medislotapp.models.Appointment

// This object holds all sample static data used in the app.
// It works like a fake database for your prototype.
object SampleData {

    // List of sample doctors shown in Doctors screen.
    // This supports the master/detail requirement.
    val doctors = listOf(
        Doctor(
            1,
            "Dr. Nethmi Dissanayake ",
            "Dermatologist",
            "City Health Clinic",
            "10+ Years",
            "LKR 3,000"
        ),
        Doctor(
            2,
            "Dr. Mohamed Fazir",
            "Physiotherapist",
            "Colombo Care Center",
            "8+ Years",
            "LKR 2,500"
        ),
        Doctor(
            3,
            "Dr. Nimal Perera",
            "Cardiologist",
            "Heart Clinic",
            "15+ Years",
            "LKR 4,000"
        )
    )

    // List of sample appointments.
    // Used to show initial data in Home screen.
    // This is static content for the prototype.
    val appointments = listOf(
        Appointment(
            id = "1", // Unique ID for recomposition and animations
            date = "May 25, 2026",
            time = "10:00 AM",
            doctorName = "Dr. Nethmi Dissanayake",
            doctorSpecialty = "Dermatologist",
            clinic = "City Health Clinic"
        )
    )
}