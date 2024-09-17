package dev.bober.auth.data.repository

import dev.bober.auth.data.model.AuthUser
import dev.bober.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun auth(email: String, password: String): Flow<Resource<Unit>>
    suspend fun deleteAuth()
    fun getCurrentUser() : AuthUser?
}