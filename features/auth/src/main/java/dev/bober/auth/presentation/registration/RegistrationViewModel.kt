package dev.bober.auth.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.bober.auth.domain.RegistrationUseCase
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val registrationUseCase: RegistrationUseCase
) : ViewModel() {

    var email : String = ""
    var password : String = ""
    var name : String = ""
    var birthday : String = ""

    fun register(email: String, name: String, password: String, birthday: String) {
        viewModelScope.launch {
            registrationUseCase(email, name, password, birthday)
        }
    }
}