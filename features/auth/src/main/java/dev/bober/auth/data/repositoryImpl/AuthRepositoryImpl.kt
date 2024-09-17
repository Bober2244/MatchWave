package dev.bober.auth.data.repositoryImpl

import android.util.Log
import dev.bober.auth.data.model.AuthUser
import dev.bober.auth.data.remote.AuthApi
import dev.bober.auth.data.remote.dto.LoginUserDto
import dev.bober.auth.data.remote.dto.RegisterUserDto
import dev.bober.auth.data.repository.AuthRepository
import dev.bober.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl(
    private val api : AuthApi
) : AuthRepository {
    override fun login(email: String, password: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        api.signIn(LoginUserDto(email, password))
    }

    override fun register(
        email: String,
        name: String,
        password: String,
        birthday: String,
    ): Flow<Resource<Unit>> = flow {
        //emit(Resource.Loading())
        api.signUp(
            RegisterUserDto(
                email = email,
                name = name,
                password = password,
                birthday = birthday,
            )
        ).fold(
            onSuccess = { emit(Resource.Success(it)) },
            onFailure = { emit(Resource.Error(it)) }
        )
        //TODO: Не работает
        Log.i("RegistrationRepository", "Registration repository")
    }

    override suspend fun deleteAuth() {
        TODO("Not yet implemented")
    }

    override fun getCurrentUser(): AuthUser? {
        TODO("Not yet implemented")
    }
}