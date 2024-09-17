package dev.bober.auth.domain

import android.util.Log
import dev.bober.auth.data.repository.AuthRepository

class RegisterUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String, name: String, password: String, birthday: String) {
        authRepository.register(email, name, password, birthday)
        Log.i("RegistrationUseCase", "Registration use case")
    }
}