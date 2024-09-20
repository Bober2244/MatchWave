package dev.bober.auth.presentation.registration.navigation

import kotlinx.serialization.Serializable

@Serializable
object RegistrationScreen

@Serializable
data class AddName(
    val email: String,
    val password: String,
    )

@Serializable
data class AddBirthday(
    val email: String,
    val password: String,
    val name : String,
)