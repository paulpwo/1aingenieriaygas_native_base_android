package com.pwol.flutter_app_1agas2.ui.login

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    val displayName: String,
    val email: String,
    val token: String,
    val image: String,
    //... other data fields that may be accessible to the UI
)