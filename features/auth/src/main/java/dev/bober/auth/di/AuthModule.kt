package dev.bober.auth.di

import dev.bober.auth.data.remote.AuthApi
import dev.bober.auth.data.repository.AuthRepository
import dev.bober.auth.data.repositoryImpl.AuthRepositoryImpl
import dev.bober.auth.domain.LoginUseCase
import dev.bober.auth.domain.RegistrationUseCase
import dev.bober.auth.domain.ValidationEmailUseCase
import dev.bober.auth.presentation.authstart.LoginScreenViewModel
import dev.bober.auth.presentation.registration.RegistrationScreenViewModel
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val authModule = module {
    //ViewModels
    viewModel { RegistrationScreenViewModel(get()) }
    viewModel { LoginScreenViewModel(get()) }
    //Repositories
    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
    //UseCases
    singleOf(::LoginUseCase)
    singleOf(::RegistrationUseCase)
    singleOf(::ValidationEmailUseCase)
}

val apiModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single {
        OkHttpClient.Builder()
            .cache(Cache(androidContext().cacheDir, 10L * 1024 * 1024))
            .build()
    }

    single {
        get<Retrofit>().create(AuthApi::class.java)
    }

}

private const val BASE_URL = "http://10.0.2.2:8000/"