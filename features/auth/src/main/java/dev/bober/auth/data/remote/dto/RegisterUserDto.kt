package dev.bober.auth.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RegisterUserDto(
    val email : String,
    val name : String,
    val password : String,
    @SerializedName("date_of_birth")
    val birthday : String,
)
