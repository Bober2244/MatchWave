package dev.bober.auth.di

import dev.bober.auth.data.repository.AuthRepository
import dev.bober.auth.data.repositoryImpl.AuthRepositoryImpl
import dev.bober.auth.domain.ValidationEmailUseCase
import dev.bober.auth.presentation.authstart.AuthScreenViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    viewModel { AuthScreenViewModel() }

    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }

    single {
        ValidationEmailUseCase()
    }
}
