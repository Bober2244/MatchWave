package dev.bober.auth.presentation.registration

import android.util.Log
import androidx.lifecycle.ViewModel
import dev.bober.auth.data.repository.AuthRepository
import dev.bober.auth.domain.RegisterUseCase

class RegistrationScreenViewModel(
    private val authRepository: AuthRepository,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    fun register(email: String, name: String, password: String, birthday: String) {
        registerUseCase(email, name, password, birthday)
        Log.i("RegistrationScreenViewModel", "Registration view model")
    }
}