package dev.bober.auth.presentation.authstart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.bober.auth.data.repository.AuthRepository
import dev.bober.auth.domain.AuthorizeUseCase
import kotlinx.coroutines.launch

class AuthScreenViewModel(
    private val authRepository : AuthRepository,
    private val authorizeUseCase: AuthorizeUseCase
) : ViewModel() {

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authorizeUseCase(email, password).collect {
            }
        }
    }
}