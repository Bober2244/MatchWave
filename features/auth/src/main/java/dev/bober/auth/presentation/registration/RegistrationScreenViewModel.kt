package dev.bober.auth.presentation.registration

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.bober.auth.data.repository.AuthRepository
import dev.bober.auth.domain.RegistrationUseCase
import kotlinx.coroutines.launch

class RegistrationScreenViewModel(
    private val registrationUseCase: RegistrationUseCase
) : ViewModel() {

    fun register(email: String, name: String, password: String, birthday: String) {
        viewModelScope.launch {
            registrationUseCase(email, name, password, birthday)
        }
    }
}