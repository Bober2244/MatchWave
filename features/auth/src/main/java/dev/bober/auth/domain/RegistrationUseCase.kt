package dev.bober.auth.domain

import android.util.Log
import dev.bober.auth.data.repository.AuthRepository
import dev.bober.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class RegistrationUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, name: String, password: String, birthday: String) {
        authRepository.register(email, name, password, birthday).map { res ->
            when(res){
                is Resource.Error -> throw Exception("Error in UseCase", res.error)
                is Resource.Loading -> Log.i("UseCaseLoading", "Loading in UseCase")
                is Resource.Success -> res.data
            }
        }.collect()
    }
}