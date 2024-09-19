package dev.bober.auth.domain

import dev.bober.auth.data.repository.AuthRepository
import dev.bober.utils.Resource
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String, password: String): Flow<Resource<Unit>> {
        return authRepository.login(email, password)
    }
}