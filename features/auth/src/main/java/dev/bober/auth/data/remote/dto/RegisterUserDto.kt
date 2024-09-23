package dev.bober.auth.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RegisterUserDto(
    val email : String,
    val password : String,
)
