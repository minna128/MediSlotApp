package com.minna.medislotapp.data

// This data class represents a Doctor in the app.
// It is used to store all doctor-related information in one object.
data class Doctor(

    // Unique ID for each doctor.
    // Used for navigation (master → detail screen).
    val id: Int,

    // Doctor's full name.
    // Displayed in Doctors screen and Detail screen.
    val name: String,

    // Doctor's medical specialty.
    // Example: Cardiologist, Dermatologist.
    val specialty: String,

    // Clinic name where the doctor works.
    val clinic: String,

    // Years of experience.
    // Stored as String for display purposes.
    val experience: String,

    // Consultation fee.
    // Stored as String (e.g., "LKR 3,000") for UI display.
    val consultationFee: String
)