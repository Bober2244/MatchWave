package dev.bober.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.bober.auth.domain.LoginUseCase
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(email, password).collect {
                loginUseCase(
                    email = email,
                    password = password
                )
            }
        }
    }
}