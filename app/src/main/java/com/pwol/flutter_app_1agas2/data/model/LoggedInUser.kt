package com.pwol.flutter_app_1agas2.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val userId: String,
    val displayName: String,
    val email: String,
    val token: String,
    val image: String,
)