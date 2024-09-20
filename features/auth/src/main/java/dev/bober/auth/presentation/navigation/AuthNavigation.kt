package dev.bober.auth.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object RegistrationScreen

@Serializable
object LoginScreen

@Serializable
data class AddNameScreen(
    val email: String,
    val password: String,
)

@Serializable
data class AddBirthdayScreen(
    val email: String,
    val password: String,
    val name: String,
)