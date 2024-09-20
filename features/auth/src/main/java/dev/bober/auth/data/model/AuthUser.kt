package dev.bober.auth.data.model

data class AuthUser(
    val email : String,
    val password : String,
    val name : String,
    val birthday : String,
)
