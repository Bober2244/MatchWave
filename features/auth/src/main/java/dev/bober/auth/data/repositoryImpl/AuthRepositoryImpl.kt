package dev.bober.auth.data.repositoryImpl

import dev.bober.auth.data.model.AuthUser
import dev.bober.auth.data.repository.AuthRepository
import dev.bober.utils.Resource
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl : AuthRepository {
    override fun auth(email: String, password: String): Flow<Resource<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAuth() {
        TODO("Not yet implemented")
    }

    override fun getCurrentUser(): AuthUser? {
        TODO("Not yet implemented")
    }
}